package com.example.a13finalucp.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Tiket
import com.example.a13finalucp.repository.TiketRepository
import kotlinx.coroutines.launch



data class InsertTktUiState(
    val insertTktUiTiket: InsertTktUiTiket = InsertTktUiTiket(),
)

data class InsertTktUiTiket(
    val id_tiket: Int = 0,
    val id_event: Int = 0,
    val id_peserta: Int = 0,
    val kapasitas_tiket: Int = 0,
    val harga_tiket: Int = 0,
)

fun InsertTktUiTiket.toTkt(): Tiket = Tiket(
    id_tiket = id_tiket,
    id_event = id_event,
    id_peserta = id_peserta,
    kapasitas_tiket = kapasitas_tiket,
    harga_tiket= harga_tiket
)

fun Tiket.toUiStateTkt(): InsertTktUiState = InsertTktUiState(
    insertTktUiTiket = toInsertTktUiTiket()
)

fun Tiket.toInsertTktUiTiket(): InsertTktUiTiket = InsertTktUiTiket(
    id_tiket = id_tiket,
    id_event = id_event,
    id_peserta = id_peserta,
    kapasitas_tiket = kapasitas_tiket,
    harga_tiket= harga_tiket
)

