package com.boringkm.metroapp.domain.exception

import com.boringkm.metroapp.domain.model.SubwayArrivalErrorCode

class ApiException(private val subwayArrivalErrorCode: SubwayArrivalErrorCode): Exception() {
    override val message = subwayArrivalErrorCode.message
}