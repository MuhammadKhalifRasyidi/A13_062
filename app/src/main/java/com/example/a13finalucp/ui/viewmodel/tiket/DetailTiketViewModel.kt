package com.example.a13finalucp.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Tiket
import com.example.a13finalucp.repository.TiketRepository
import kotlinx.coroutines.launch



data class DetailTktUiState(
    val detailTktUiTiket: InsertTktUiTiket = InsertTktUiTiket(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiTiketNotEmpty: Boolean
        get() = detailTktUiTiket != InsertTktUiTiket()
}

fun Tiket.toDetailTktUiTiket(): InsertTktUiTiket {
    return InsertTktUiTiket(
        id_tiket = id_tiket,
        id_event = id_event,
        id_peserta = id_peserta,
        kapasitas_tiket = kapasitas_tiket,
        harga_tiket = harga_tiket
    )
}