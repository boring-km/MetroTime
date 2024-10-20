package com.boringkm.metroapp.data

import com.boringkm.metroapp.data.model.SubwayArrivalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SubwayAPI {
    @GET("subway/{apiKey}/json/realtimeStationArrival/0/10/{stationName}")
    suspend fun getRealtimeStationArrival(
        @Path("apiKey") apiKey: String,
        @Path("stationName") stationName: String
    ): Response<SubwayArrivalResponse>
}