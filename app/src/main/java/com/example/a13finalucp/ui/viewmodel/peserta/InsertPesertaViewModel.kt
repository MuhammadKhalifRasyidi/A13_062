package com.example.a13finalucp.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.repository.PesertaRepository
import kotlinx.coroutines.launch



fun Peserta.toUiStatePst(): InsertPstUiState = InsertPstUiState(
    insertPstUiPeserta = toInsertPstUiPeserta()
)

fun Peserta.toInsertPstUiPeserta(): InsertPstUiPeserta = InsertPstUiPeserta(
    id_peserta = id_peserta,
    nama_peserta = nama_peserta,
    email = email,
    nomor_telepon = nomor_telepon
)