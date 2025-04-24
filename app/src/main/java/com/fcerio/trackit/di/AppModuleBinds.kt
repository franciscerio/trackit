package com.fcerio.trackit.di

import com.fcerio.core.common.di.scope.AuthInterceptorScope
import com.fcerio.core.domain.SessionProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModuleBinds {

    @Binds
    @Singleton
    abstract fun bindSessionProvider(provider: SampleSessionProvider): SessionProvider

    @Binds
    @Singleton
    @AuthInterceptorScope
    abstract fun bindAuthInterceptor(provider: AuthInterceptor): Interceptor
}
