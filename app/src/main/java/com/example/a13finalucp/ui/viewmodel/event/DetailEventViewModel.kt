package com.example.a13finalucp.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Event
import com.example.a13finalucp.repository.EventRepository
import kotlinx.coroutines.launch

class DetailEventViewModel(private val evtRepository: EventRepository) : ViewModel() {

    var uiState by mutableStateOf(DetailEvtUiState())
        private set

    fun fetchDetailEvent(id_event: Int) {
        viewModelScope.launch {
            uiState = DetailEvtUiState(isLoading = true)
            try {
                val event = evtRepository.getEventByIdEvent(id_event)
                uiState = DetailEvtUiState(detailEvtUiEvent = event.data.toDetailEvtUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = DetailEvtUiState(isError = true, errorMessage = "Failed to fetch details: ${e.message}")
            }
        }
    }
}

data class DetailEvtUiState(
    val detailEvtUiEvent: InsertEvtUiEvent = InsertEvtUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
) {
    val isUiEventNotEmpty: Boolean
        get() = detailEvtUiEvent != InsertEvtUiEvent()
}

fun Event.toDetailEvtUiEvent(): InsertEvtUiEvent {
    return InsertEvtUiEvent(
        id_event = id_event,
        nama_event = nama_event,
        deskripsi_event = deskripsi_event,
        tanggal_event = tanggal_event,
        lokasi_event = lokasi_event
    )
}