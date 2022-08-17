package com.mobilejazz.wildhike.features.home.data.usecase

import com.mobilejazz.wildhike.features.home.data.repository.IHomeRepository
import javax.inject.Inject

class DeleteCurrentUserTokenUseCase @Inject constructor(
    private val homeRepository: IHomeRepository
) {
    operator fun invoke() = homeRepository.deleteCurrentUserToken()
}