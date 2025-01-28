package com.example.a13finalucp.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.repository.EventRepository
import com.example.a13finalucp.ui.view.event.DestinasiUpdateEvent
import kotlinx.coroutines.launch

class UpdateEventViewModel (
    savedStateHandle: SavedStateHandle,
    private val eventRepository: EventRepository
): ViewModel(){
    var UpdateEvtUiState by mutableStateOf(InsertEvtUiState())
        private set

    private val _id_event: Int = checkNotNull(savedStateHandle[DestinasiUpdateEvent.IDEvent])

    init {
        viewModelScope.launch {
            UpdateEvtUiState = eventRepository.getEventByIdEvent(_id_event)
                .data.toUiStateEvt()
        }
    }

    fun UpdateInsertEvtState(insertEvtUiEvent: InsertEvtUiEvent){
        UpdateEvtUiState = InsertEvtUiState(insertEvtUiEvent = insertEvtUiEvent)
    }

    suspend fun updateEvt(){
        viewModelScope.launch {
            try {
                eventRepository.updateEvent(_id_event, UpdateEvtUiState.insertEvtUiEvent.toEvt())
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}