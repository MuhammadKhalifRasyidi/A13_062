package com.example.a13finalucp.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.repository.TiketRepository
import com.example.a13finalucp.ui.view.tiket.DestinasiUpdateTiket
import kotlinx.coroutines.launch

class UpdateTiketViewModel (
    savedStateHandle: SavedStateHandle,
    private val tiketRepository: TiketRepository
): ViewModel(){
    var UpdateTktUiState by mutableStateOf(InsertTktUiState())
        private set

    private val _id_tiket: Int = checkNotNull(savedStateHandle[DestinasiUpdateTiket.IDTiket])

    init {
        viewModelScope.launch {
            UpdateTktUiState = tiketRepository.getTiketByIdTiket(_id_tiket)
                .data.toUiStateTkt()
        }
    }

    fun UpdateInsertTktState(insertTktUiTiket: InsertTktUiTiket){
        UpdateTktUiState = InsertTktUiState(insertTktUiTiket = insertTktUiTiket)
    }

    suspend fun updateTkt(){
        viewModelScope.launch {
            try {
                tiketRepository.updateTiket(_id_tiket, UpdateTktUiState.insertTktUiTiket.toTkt())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}