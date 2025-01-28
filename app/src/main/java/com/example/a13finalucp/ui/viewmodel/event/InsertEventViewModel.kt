package com.example.a13finalucp.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Event
import com.example.a13finalucp.repository.EventRepository
import kotlinx.coroutines.launch

class InsertEventViewModel(private val evt: EventRepository) : ViewModel() {
    var uiState by mutableStateOf(InsertEvtUiState())
        private set


    fun UpdateInsertEvtState(insertEvtUiEvent: InsertEvtUiEvent) {
        uiState = InsertEvtUiState(insertEvtUiEvent = insertEvtUiEvent)
    }


    suspend fun insertEvt() {
        viewModelScope.launch {
            try {
                evt.insertEvent(uiState.insertEvtUiEvent.toEvt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class InsertEvtUiState(
    val insertEvtUiEvent: InsertEvtUiEvent = InsertEvtUiEvent()
)

data class InsertEvtUiEvent(
    val id_event: Int = 0,
    val nama_event: String = "",
    val deskripsi_event: String = "",
    val tanggal_event: String = "",
    val lokasi_event: String = ""
)

fun InsertEvtUiEvent.toEvt(): Event = Event(
    id_event = id_event,
    nama_event = nama_event,
    deskripsi_event = deskripsi_event,
    tanggal_event = tanggal_event,
    lokasi_event = lokasi_event
)

fun Event.toUiStateEvt(): InsertEvtUiState = InsertEvtUiState(
    insertEvtUiEvent = toInsertEvtUiEvent()
)

fun Event.toInsertEvtUiEvent(): InsertEvtUiEvent = InsertEvtUiEvent(
    id_event = id_event,
    nama_event = nama_event,
    deskripsi_event = deskripsi_event,
    tanggal_event = tanggal_event,
    lokasi_event = lokasi_event
)