package com.example.a13finalucp.ui.viewmodel.transaksi

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Transaksi
import com.example.a13finalucp.repository.TransaksiRepository
import kotlinx.coroutines.launch



data class DetailTssUiState(
    val detailTssUiTransaksi: InsertTssUiTransaksi = InsertTssUiTransaksi(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiTransaksiNotEmpty: Boolean
        get() = detailTssUiTransaksi != InsertTssUiTransaksi()
}

fun Transaksi.toDetailTssUiTransaksi(): InsertTssUiTransaksi {
    return InsertTssUiTransaksi(
        id_transaksi = id_transaksi,
        id_tiket = id_tiket,
        jumlah_tiket = jumlah_tiket,
        jumlah_pembayaran = jumlah_pembayaran,
        tanggal_transaksi = tanggal_transaksi
    )
}