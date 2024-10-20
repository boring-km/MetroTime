package com.boringkm.metroapp.domain

import com.boringkm.metroapp.domain.model.SubwayArrival
import kotlinx.coroutines.flow.Flow

interface RealtimeStationArrivalRepository {
    fun searchSubwayArrival(subwayName: String): Flow<List<SubwayArrival>>
}