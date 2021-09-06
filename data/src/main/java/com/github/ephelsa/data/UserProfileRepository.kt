package com.github.ephelsa.data

import com.github.ephelsa.domain.UserInformation

interface UserProfileRepository {

    suspend fun getUserInformation(): UserInformation

    suspend fun getUserThumbnail(): String
}