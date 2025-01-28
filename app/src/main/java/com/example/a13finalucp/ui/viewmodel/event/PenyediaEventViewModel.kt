package com.example.a13finalucp.ui.viewmodel.event

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.a13finalucp.App

object PenyediaEventViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeEventViewModel(aplikasiKontak().container.eventRepository)
        }

        initializer {
            InsertEventViewModel(aplikasiKontak().container.eventRepository)
        }

        initializer {
            DetailEventViewModel(aplikasiKontak().container.eventRepository)
        }

        initializer {
            UpdateEventViewModel(createSavedStateHandle(),aplikasiKontak().container.eventRepository
            )
        }
    }
}

fun CreationExtras.aplikasiKontak(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)