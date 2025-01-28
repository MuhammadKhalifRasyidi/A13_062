package com.example.a13finalucp.ui.viewmodel.peserta

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a13finalucp.App

object PenyediaPesertaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomePesertaViewModel(aplikasiKontak().container.pesertaRepository)
        }

        initializer {
            InsertPesertaViewModel(aplikasiKontak().container.pesertaRepository)
        }

        initializer {
            DetailPesertaViewModel(aplikasiKontak().container.pesertaRepository)
        }

        initializer {
            UpdatePesertaViewModel(createSavedStateHandle(),aplikasiKontak().container.pesertaRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)