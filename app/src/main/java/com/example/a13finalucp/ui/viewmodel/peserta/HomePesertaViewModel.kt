package com.example.a13finalucp.ui.viewmodel.peserta

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.repository.PesertaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomePesertaUiState {
    data class Success(val peserta: List<Peserta>) : HomePesertaUiState()
    object Error : HomePesertaUiState()
    object Loading : HomePesertaUiState()
}

class HomePesertaViewModel(private val pst: PesertaRepository) : ViewModel() {
    var pstUIState: HomePesertaUiState by mutableStateOf(HomePesertaUiState.Loading)
        private set

    init {
        getPst()
    }

    fun getPst() {
        viewModelScope.launch {
            pstUIState = HomePesertaUiState.Loading
            pstUIState = try {
                HomePesertaUiState.Success(pst.getPeserta().data)
            } catch (e: IOException) {
                HomePesertaUiState.Error
            } catch (e: HttpException) {
                HomePesertaUiState.Error
            }
        }
    }

    fun deletePst(id_peserta: Int) {
        viewModelScope.launch {
            try {
                pst.deletePeserta(id_peserta)
            } catch (e: IOException) {
                HomePesertaUiState.Error
            } catch (e: HttpException) {
                HomePesertaUiState.Error
            }
        }
    }
}