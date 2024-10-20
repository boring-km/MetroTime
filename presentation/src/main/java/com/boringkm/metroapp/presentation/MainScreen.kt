package com.boringkm.metroapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boringkm.metroapp.domain.model.SubwayArrival
import com.boringkm.metroapp.presentation.theme.MetroTimeTheme

@Composable
fun MainScreenComposable(viewModel: MainViewModel) {
    val errorMessage = viewModel.errorMessage.collectAsState()
    val subwayArrivals = viewModel.subwayArrivalList.collectAsState()

    MainScreen(
        errorMessage = errorMessage,
        subwayArrivals = subwayArrivals,
        onSearchMetro = { viewModel.searchMetro(it) }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    errorMessage: State<String> = mutableStateOf(""),
    subwayArrivals: State<List<SubwayArrival>> = mutableStateOf(listOf()),
    onSearchMetro: (String) -> Unit = {}
) {

    Scaffold {
        val scrollState = rememberScrollState()
        val inputText = remember { mutableStateOf("") }
        Column(
            Modifier
                .fillMaxSize()
                .scrollable(scrollState, orientation = Orientation.Vertical)
                .padding(4.dp)
                .padding(top = 24.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                TextField(
                    value = inputText.value,
                    onValueChange = {
                        inputText.value = it
                    },
                    singleLine = true,
                )
                Button(onClick = { onSearchMetro(inputText.value) }) {
                    Text("Search")
                }
            }
            Text(text = errorMessage.value)
            subwayArrivals.value.forEach {
                Text(text = it.getJsonString(), modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MetroTimeTheme {
        MainScreen(
            subwayArrivals = remember {
                mutableStateOf(
                    listOf(
                        SubwayArrival(
                            subwayIdName = "subwayIdName",
                            upDownLine = "upDownLine",
                            trainLineName = "trainLineName",
                            lastTrainStation = "lastTrainStation",
                            trainType = "trainType",
                            message1 = "message1",
                            message2 = "message2",
                            arrivalMessage = "arrivalMessage",
                            isLastTrain = true
                        )
                    )
                )
            }
        )
    }
}