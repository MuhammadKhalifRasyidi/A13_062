package com.example.a13finalucp.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.repository.PesertaRepository
import com.example.a13finalucp.ui.view.peserta.DestinasiUpdatePeserta
import kotlinx.coroutines.launch

class UpdatePesertaViewModel (
    savedStateHandle: SavedStateHandle,
    private val pesertaRepository: PesertaRepository
): ViewModel(){
    var UpdatePstUiState by mutableStateOf(InsertPstUiState())
        private set

    private val _id_peserta: Int = checkNotNull(savedStateHandle[DestinasiUpdatePeserta.IDPeserta])

    init {
        viewModelScope.launch {
            UpdatePstUiState = pesertaRepository.getPesertaByIdPeserta(_id_peserta)
                .data.toUiStatePst()
        }
    }

    fun UpdateInsertPstState(insertPstUiPeserta: InsertPstUiPeserta){
        UpdatePstUiState = InsertPstUiState(insertPstUiPeserta = insertPstUiPeserta)
    }

    suspend fun updatePst(){
        viewModelScope.launch {
            try {
                pesertaRepository.updatePeserta(_id_peserta, UpdatePstUiState.insertPstUiPeserta.toPst())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}