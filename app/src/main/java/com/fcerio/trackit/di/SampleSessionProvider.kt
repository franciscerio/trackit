package com.fcerio.trackit.di

import com.fcerio.core.common.toBearerToken
import com.fcerio.core.domain.SessionProvider
import javax.inject.Inject

class SampleSessionProvider @Inject constructor() : SessionProvider {
    override suspend fun getAccessToken(): String = "1275|5g3JjUGGBMZqLq6b0qj1bPZPFvLSnOwxYJHjbZK2".toBearerToken()

    override suspend fun getUserId(): Long = 0L
}
