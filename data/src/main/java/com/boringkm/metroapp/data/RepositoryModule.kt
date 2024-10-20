package com.boringkm.metroapp.data

import com.boringkm.metroapp.domain.RealtimeStationArrivalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRealtimeStationArrivalRepository(subwayAPI: SubwayAPI): RealtimeStationArrivalRepository {
        return SubwayRepositoryImpl(subwayAPI)
    }
}