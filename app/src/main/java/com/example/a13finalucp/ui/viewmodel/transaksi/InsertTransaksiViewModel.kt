package com.example.a13finalucp.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Transaksi
import com.example.a13finalucp.repository.TransaksiRepository
import kotlinx.coroutines.launch

class InsertTransaksiViewModel(private val tss: TransaksiRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertTssUiState())
        private set


    fun UpdateInsertTssState(insertTssUiTransaksi: InsertTssUiTransaksi) {
        uiState = InsertTssUiState(insertTssUiTransaksi = insertTssUiTransaksi)
    }


    suspend fun insertTss() {
        viewModelScope.launch {
            try {
                tss.insertTransaksi(uiState.insertTssUiTransaksi.toTss())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertTssUiState(
    val insertTssUiTransaksi: InsertTssUiTransaksi = InsertTssUiTransaksi()
)

data class InsertTssUiTransaksi(
    val id_transaksi: Int = 0,
    val id_tiket: Int = 0,
    val jumlah_tiket: Int = 0,
    val jumlah_pembayaran: Int = 0,
    val tanggal_transaksi: String = "",
)

fun InsertTssUiTransaksi.toTss(): Transaksi = Transaksi(
    id_transaksi = id_transaksi,
    id_tiket = id_tiket,
    jumlah_tiket = jumlah_tiket,
    jumlah_pembayaran = jumlah_pembayaran,
    tanggal_transaksi = tanggal_transaksi
)

fun Transaksi.toUiStateTss(): InsertTssUiState = InsertTssUiState(
    insertTssUiTransaksi = toInsertTssUiTransaksi()
)

fun Transaksi.toInsertTssUiTransaksi(): InsertTssUiTransaksi = InsertTssUiTransaksi(
    id_transaksi = id_transaksi,
    id_tiket = id_tiket,
    jumlah_tiket = jumlah_tiket,
    jumlah_pembayaran = jumlah_pembayaran,
    tanggal_transaksi = tanggal_transaksi
)