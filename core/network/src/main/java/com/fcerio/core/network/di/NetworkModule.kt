package com.fcerio.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.fcerio.core.common.di.scope.AuthInterceptorScope
import com.fcerio.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import timber.log.Timber
import javax.inject.Singleton

private const val TIME_OUT = 300000L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpEngine(
        interceptor: ChuckerInterceptor,
        @AuthInterceptorScope authInterceptor: Interceptor?
    ): HttpClientEngine {
        return OkHttp.create {
            addInterceptor(interceptor)
            if (authInterceptor != null) {
                addInterceptor(authInterceptor)
            }
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        engine: HttpClientEngine,
        interceptor: ChuckerInterceptor,
        @AuthInterceptorScope authInterceptor: Interceptor?
    ): HttpClient {
        return HttpClient(engine) {
            expectSuccess = true

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = BuildConfig.BASE_URL_HOST
                    // path("/api/v1")
                }

                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

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
                level = if (BuildConfig.DEBUG) {
                    LogLevel.ALL
                } else {
                    LogLevel.HEADERS
                }
                logger = Logger.DEFAULT
            }
            //Http Response
            install(ResponseObserver) {
                onResponse { response ->
                    Timber.d("HTTP status:", "${response.status.value}")
                }
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
                socketTimeoutMillis = TIME_OUT
                connectTimeoutMillis = TIME_OUT
            }
        }
    }

    @Provides
    @Singleton
    fun providesChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor
            .Builder(context)
            .collector(ChuckerCollector(context, true))
            .maxContentLength(200_000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
}