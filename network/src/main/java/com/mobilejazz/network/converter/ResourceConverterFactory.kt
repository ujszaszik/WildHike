package com.mobilejazz.network.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.Moshi
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.*

class ResourceConverterFactory private constructor(
    private val moshi: Moshi,
) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return ResourceResponseBodyConverter<Any, Any>(moshi, type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter: JsonAdapter<Any> =
            moshi.adapter(type, jsonAnnotations(parameterAnnotations))
        return ResourceRequestBodyConverter(adapter)
    }

    companion object {
        @JvmOverloads
        fun create(moshi: Moshi? = Moshi.Builder().build()): ResourceConverterFactory {
            return moshi?.let {
                ResourceConverterFactory(it)
            } ?: throw NullPointerException("moshi == null")
        }

        private fun jsonAnnotations(annotations: Array<Annotation>): Set<Annotation?> {
            var result: MutableSet<Annotation?>? = null
            for (annotation in annotations) {
                if (annotation.javaClass.isAnnotationPresent(JsonQualifier::class.java)) {
                    if (result == null) result = LinkedHashSet()
                    result.add(annotation)
                }
            }
            return result?.let { Collections.unmodifiableSet(it) } ?: emptySet<Annotation>()
        }
    }
}