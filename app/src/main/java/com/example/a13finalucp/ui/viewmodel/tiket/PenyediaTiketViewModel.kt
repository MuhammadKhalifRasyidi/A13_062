package com.example.a13finalucp.ui.viewmodel.tiket

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a13finalucp.App

object PenyediaTiketViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeTiketViewModel(aplikasiKontak().container.tiketRepository)
        }

        initializer {
            InsertTiketViewModel(aplikasiKontak().container.tiketRepository)
        }

        initializer {
            DetailTiketViewModel(aplikasiKontak().container.tiketRepository)
        }

        initializer {
            UpdateTiketViewModel(createSavedStateHandle(),aplikasiKontak().container.tiketRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)