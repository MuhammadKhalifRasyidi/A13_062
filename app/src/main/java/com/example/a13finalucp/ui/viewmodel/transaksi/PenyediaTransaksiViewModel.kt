package com.example.a13finalucp.ui.viewmodel.transaksi

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a13finalucp.App

object PenyediaTransaksiViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeTransaksiViewModel(aplikasiKontak().container.transaksiRepository)
        }

        initializer {
            InsertTransaksiViewModel(aplikasiKontak().container.transaksiRepository)
        }

        initializer {
            DetailTransaksiViewModel(aplikasiKontak().container.transaksiRepository)
        }

        initializer {
            UpdateTransaksiViewModel(createSavedStateHandle(),aplikasiKontak().container.transaksiRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)