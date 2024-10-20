package com.boringkm.metroapp.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boringkm.metroapp.domain.RealtimeStationArrivalRepository
import com.boringkm.metroapp.domain.model.SubwayArrival
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RealtimeStationArrivalRepository) :
    ViewModel() {
    fun searchMetro(searchMetroName: String) {
        repository.searchSubwayArrival(searchMetroName).onEach {
            subwayArrivalListState.emit(it)
        }.catch {
            errorMessageState.emit(it.message ?: "Error fetching subway arrival data")
        }.launchIn(viewModelScope)

    }

    private val errorMessageState = MutableStateFlow("")
    val errorMessage = errorMessageState.asStateFlow()

    private val subwayArrivalListState = MutableStateFlow(listOf<SubwayArrival>())
    val subwayArrivalList = subwayArrivalListState.asStateFlow()

}