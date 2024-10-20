package com.boringkm.metroapp.data.model

import com.boringkm.metroapp.domain.model.SubwayArrival

data class SubwayArrivalResponse(
    val errorMessage: ErrorMessage,
    val realtimeArrivalList: List<RealtimeArrival>
)

data class ErrorMessage(
    val status: Int,
    val code: String,
    val message: String,
    val link: String,
    val developerMessage: String,
    val total: Int
)

data class RealtimeArrival(
    val totalCount: Int,
    val rowNum: Int,
    val selectedCount: Int,
    val subwayId: Int,
    val updnLine: String,
    val trainLineNm: String,
    val subwayHeading: Any,
    val statnFid: String,
    val statnTid: String,
    val statnId: String,
    val statnNm: String,
    val trnsitCo: String,
    val ordkey: String,
    val subwayList: String,
    val statnList: String,
    val btrainSttus: String,
    val barvlDt: String,
    val btrainNo: String,
    val bstatnId: String,
    val bstatnNm: String,
    val recptnDt: String,
    val arvlMsg2: String,
    val arvlMsg3: String,
    val arvlCd: String,
    val lstcarAt: String
) {
    fun transformToSubwayArrival(): SubwayArrival {
        return SubwayArrival(
            subwayIdName = getSubwayIdName(),
            upDownLine = updnLine,
            trainLineName = trainLineNm,
            lastTrainStation = bstatnNm,
            trainType = trnsitCo,
            message1 = arvlMsg2,
            message2 = arvlMsg3,
            arrivalMessage = getArrivalMessage(),
            isLastTrain = lstcarAt == "1"
        )
    }

    // (0:진입, 1:도착, 2:출발, 3:전역출발, 4:전역진입, 5:전역도착, 99:운행중)
    private fun getArrivalMessage(): String {
        return when (arvlCd) {
            "0" -> "진입"
            "1" -> "도착"
            "2" -> "출발"
            "3" -> "전역출발"
            "4" -> "전역진입"
            "5" -> "전역도착"
            "99" -> "운행중"
            else -> "기타"
        }
    }

    // (1001:1호선, 1002:2호선, 1003:3호선, 1004:4호선, 1005:5호선 1006:6호선, 1007:7호선, 1008:8호선, 1009:9호선, 1061:중앙선1063:경의중앙선, 1065:공항철도, 1067:경춘선, 1075:수의분당선 1077:신분당선, 1092:우이신설선, 1093:서해선, 1081:경강선, 1032:GTX-A)
    private fun getSubwayIdName(): String {
        return when (subwayId) {
            1001 -> "1호선"
            1002 -> "2호선"
            1003 -> "3호선"
            1004 -> "4호선"
            1005 -> "5호선"
            1006 -> "6호선"
            1007 -> "7호선"
            1008 -> "8호선"
            1009 -> "9호선"
            1061 -> "중앙선"
            1063 -> "경의중앙선"
            1065 -> "공항철도"
            1067 -> "경춘선"
            1075 -> "수인분당선"
            1077 -> "신분당선"
            1092 -> "우이신설선"
            1093 -> "서해선"
            1081 -> "경강선"
            1032 -> "GTX-A"
            else -> "기타"
        }
    }
}