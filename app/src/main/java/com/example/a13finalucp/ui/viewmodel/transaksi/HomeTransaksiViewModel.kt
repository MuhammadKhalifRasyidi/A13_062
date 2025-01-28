package com.example.a13finalucp.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Transaksi
import com.example.a13finalucp.repository.TransaksiRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeTransaksiUiState {
    data class Success(val transaksi: List<Transaksi>) : HomeTransaksiUiState()
    object Error : HomeTransaksiUiState()
    object Loading : HomeTransaksiUiState()
}

class HomeTransaksiViewModel(private val tss: TransaksiRepository) : ViewModel() {
    var tssUIState: HomeTransaksiUiState by mutableStateOf(HomeTransaksiUiState.Loading)
        private set

    init {
        getTss()
    }

    fun getTss() {
        viewModelScope.launch {
            tssUIState = HomeTransaksiUiState.Loading
            tssUIState = try {
                HomeTransaksiUiState.Success(tss.getTransaksi().data)
            } catch (e: IOException) {
                HomeTransaksiUiState.Error
            } catch (e: HttpException) {
                HomeTransaksiUiState.Error
            }
        }
    }

    fun deleteTss(id_transaksi: Int) {
        viewModelScope.launch {
            try {
                tss.deleteTransaksi(id_transaksi)
            } catch (e: IOException) {
                HomeTransaksiUiState.Error
            } catch (e: HttpException) {
                HomeTransaksiUiState.Error
            }
        }
    }
}