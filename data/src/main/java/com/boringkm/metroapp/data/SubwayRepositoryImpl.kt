package com.boringkm.metroapp.data

import com.boringkm.metroapp.domain.RealtimeStationArrivalRepository
import com.boringkm.metroapp.domain.exception.ApiException
import com.boringkm.metroapp.domain.model.SubwayArrivalErrorCode
import kotlinx.coroutines.flow.flow

class SubwayRepositoryImpl(private val api: SubwayAPI): RealtimeStationArrivalRepository {
    override fun searchSubwayArrival(subwayName: String) = flow {
        val response = api.getRealtimeStationArrival(BuildConfig.API_KEY, subwayName)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null && body.realtimeArrivalList.isNotEmpty()) {
                if (body.errorMessage.code == SubwayArrivalErrorCode.INFO_000.code) {
                    emit(body.realtimeArrivalList.map {
                        it.transformToSubwayArrival()
                    })
                } else {
                    throw ApiException(SubwayArrivalErrorCode.fromCode(body.errorMessage.code))
                }
            } else {
                throw Exception("body is null or empty")
            }
        } else {
            throw Exception("Error fetching subway arrival data")
        }
    }
}