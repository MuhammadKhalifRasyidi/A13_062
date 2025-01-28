package com.example.a13finalucp.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.repository.PesertaRepository
import kotlinx.coroutines.launch



data class DetailPstUiState(
    val detailPstUiPeserta: InsertPstUiPeserta = InsertPstUiPeserta(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiPesertaNotEmpty: Boolean
        get() = detailPstUiPeserta != InsertPstUiPeserta()
}

fun Peserta.toDetailPstUiPeserta(): InsertPstUiPeserta {
    return InsertPstUiPeserta(
        id_peserta = id_peserta,
        nama_peserta = nama_peserta,
        email = email,
        nomor_telepon = nomor_telepon
    )
}