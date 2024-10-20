package com.boringkm.metroapp.domain.model

data class SubwayArrival(
    val subwayIdName: String,
    val upDownLine: String,
    val trainLineName: String,
    val lastTrainStation: String,
    val trainType: String,  // 열차종류 (급행, ITX, 일반, 특급)
    val message1: String,
    val message2: String,
    val arrivalMessage: String,
    val isLastTrain: Boolean
) {
    fun getJsonString(): String {
        return """
            {
                "지하철 호선": "$subwayIdName",
                "상하행선 구분": "$upDownLine",
                "지하철 호선명": "$trainLineName",
                "도착지 방면": "$lastTrainStation",
                "열차 종류": "$trainType",
                "첫번째 도착 메시지": "$message1",
                "두번째 도착 메시지": "$message2",
                "도착 상태": "$arrivalMessage",
                "막차 여부": $isLastTrain
            }
        """.trimIndent()
    }
}