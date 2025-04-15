package com.fcerio.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.fcerio.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Singleton

private const val TIME_OUT = 6000L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(
        engine: HttpClientEngine
    ): HttpClient {
        val httpClient = HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Resources)
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
            defaultRequest {
                url(BuildConfig.BASE_URL)
            }
            //Http Response
            install(ResponseObserver) {
                onResponse { response ->
                    Timber.d("HTTP status:", "${response.status.value}")
                }
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
            }
        }
        return httpClient
    }

    @Provides
    @Singleton
    fun providesChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor
            .Builder(context)
            .collector(ChuckerCollector(context, false))
            .maxContentLength(200_000L)
            .alwaysReadResponseBody(true)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpEngine(interceptor: ChuckerInterceptor): HttpClientEngine {
        return OkHttp.create {
            addInterceptor(interceptor)
        }
    }
}