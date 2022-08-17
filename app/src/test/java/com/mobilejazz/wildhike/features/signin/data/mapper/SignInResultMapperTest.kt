package com.mobilejazz.wildhike.features.signin.data.mapper

import com.mobilejazz.wildhike.features.signin.data.datasource.remote.SignInResponse
import com.mobilejazz.wildhike.features.signin.data.model.SignInResult
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SignInResultMapperTest {

    companion object {
        private const val TEST_TOKEN = "Test Token"
    }

    private val testSignInResponse = SignInResponse(TEST_TOKEN)

    @Test
    fun `test sign in result mapper, when response mapped, then token is appropriate`() {
        val result = SignInResultMapper().map(testSignInResponse)
        assertTrue(TEST_TOKEN == result.token)
    }

    @Test
    fun `test sign in result mapper, when response mapped, then mapped model is appropriate`() {
        val result = SignInResultMapper().map(testSignInResponse)
        val expectedOutcome = SignInResult(TEST_TOKEN)
        assertTrue(expectedOutcome == result)
    }
}