package com.mobilejazz.wildhike.features.home.data.mapper

import com.mobilejazz.wildhike.features.home.data.datasource.remote.UserResponse
import com.mobilejazz.wildhike.features.home.data.model.UserInfo
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserDataResponseMapperTest {

    private val testUserResponse =
        UserResponse(
            id = 1,
            email = "test@email.com",
            firstName = "Test",
            lastName = "Name",
            role = 1
        )

    @Test
    fun `test user data response mapper, when response mapped, then username is appropriate`() {
        val result = UserResponseMapper().map(testUserResponse)
        val expectedOutcome = "Test Name"
        assertTrue(expectedOutcome == result.username)
    }

    @Test
    fun `test user data response mapper, when response mapped, then mapped model is appropriate`() {
        val result = UserResponseMapper().map(testUserResponse)
        val expectedOutcome = UserInfo("Test Name")
        assertTrue(expectedOutcome == result)
    }
}