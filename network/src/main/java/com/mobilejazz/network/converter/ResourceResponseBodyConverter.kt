package com.mobilejazz.network.converter

import com.mobilejazz.extension.simpleName
import com.mobilejazz.network.call.Resource
import com.mobilejazz.network.mapper.DataMappedFrom
import com.mobilejazz.network.mapper.DataMappingException
import com.mobilejazz.network.mapper.ResponseMapper
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody
import okio.ByteString
import okio.ByteString.Companion.decodeHex
import retrofit2.Converter
import timber.log.Timber
import java.lang.reflect.Type
import kotlin.reflect.KClass

class ResourceResponseBodyConverter<T, R>(private val moshi: Moshi, private val type: Type) :
    Converter<ResponseBody, Resource<R>> {

    @Suppress("UNCHECKED_CAST")
    override fun convert(value: ResponseBody): Resource<R> {
        val source = value.source()
        if (source.rangeEquals(0, UTF8_BOM)) {
            source.skip(UTF8_BOM.size.toLong())
        }
        val rawString = value.string()
        return try {
            val adapter = moshi.adapter(getResponseClass().java)
            val result = adapter.fromJson(rawString)
            val mapper = ResponseMapper<T, R>(result!! as T)
            val mappedResponse = mapper.mapResponse()
            Resource.success(mappedResponse)
        } catch (e: Exception) {
            Timber.i(e, "Could not convert API response")
            Resource.error()
        }
    }

    private fun getResponseClass(): KClass<*> {
        return Class.forName(type.simpleName)
            .getAnnotation(DataMappedFrom::class.java)
            ?.mapperClass
            ?: throw DataMappingException.forUnableToExtractResponseClass(type.simpleName)
    }

    companion object {
        private val UTF8_BOM: ByteString = "EFBBBF".decodeHex()
    }
}