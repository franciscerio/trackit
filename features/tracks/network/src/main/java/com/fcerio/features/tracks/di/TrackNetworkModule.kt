package com.fcerio.features.tracks.di

import com.fcerio.features.tracks.network.TracksApiServices
import com.fcerio.features.tracks.network.TracksApiServicesImpl
import com.fcerio.features.tracks.network.remote.TrackRemoteSource
import com.fcerio.features.tracks.network.remote.TrackRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TrackNetworkRemoteModule {

    @Binds
    @Singleton
    abstract fun bindTrackRemoteSource(provider: TrackRemoteSourceImpl): TrackRemoteSource

    @Binds
    @Singleton
    abstract fun bindTracksApiServices(provider: TracksApiServicesImpl): TracksApiServices
}
