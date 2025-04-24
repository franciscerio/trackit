package com.fcerio.trackit.di

import com.fcerio.core.domain.SessionProvider
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sessionProvider: SessionProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // Check if Authorization header is present, then we skip
        if (!chain.request().header("Authorization").isNullOrBlank()) {
            return chain.proceed(chain.request())
        }

        // Check if the request URL matches the excluded pattern
        if (chain.request().url.toString().contains(EXCLUDED_URL_PATTERN, ignoreCase = true)) {
            return chain.proceed(chain.request())
        }

        var token: String

        runBlocking {
            token = sessionProvider.getAccessToken()
        }

        if (token.isBlank()) {
            return chain.proceed(chain.request())
        }
        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val EXCLUDED_URL_PATTERN = "amazonaws.com"
    }
}
