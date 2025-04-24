package com.fcerio.features.tracks.data.di

import com.fcerio.features.tracks.data.TrackRepository
import com.fcerio.features.tracks.data.TrackRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TrackRepositoryModuleBinds {

    @Binds
    @Singleton
    abstract fun bindTrackRepositorySource(provider: TrackRepositoryImpl): TrackRepository
}
