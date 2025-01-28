package com.example.a13finalucp.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Event
import com.example.a13finalucp.repository.EventRepository
import kotlinx.coroutines.launch



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