package com.example.a13finalucp.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.repository.TransaksiRepository
import com.example.a13finalucp.ui.view.transaksi.DestinasiUpdateTransaksi
import kotlinx.coroutines.launch

class UpdateTransaksiViewModel (
    savedStateHandle: SavedStateHandle,
    private val transaksiRepository: TransaksiRepository
): ViewModel(){
    var UpdateTssUiState by mutableStateOf(InsertTssUiState())
        private set

    private val _id_transaksi: Int = checkNotNull(savedStateHandle[DestinasiUpdateTransaksi.IDTransaksi])

    init {
        viewModelScope.launch {
            UpdateTssUiState = transaksiRepository.getTransaksiByIdTransaksi(_id_transaksi)
                .data.toUiStateTss()
        }
    }

    fun UpdateInsertTssState(insertTssUiTransaksi: InsertTssUiTransaksi){
        UpdateTssUiState = InsertTssUiState(insertTssUiTransaksi = insertTssUiTransaksi)
    }

    suspend fun updateTss(){
        viewModelScope.launch {
            try {
                transaksiRepository.updateTransaksi(_id_transaksi, UpdateTssUiState.insertTssUiTransaksi.toTss())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}