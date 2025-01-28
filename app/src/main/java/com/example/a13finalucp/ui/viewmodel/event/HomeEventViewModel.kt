package com.example.a13finalucp.ui.viewmodel.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a13finalucp.model.Event
import com.example.a13finalucp.repository.EventRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeEventUiState {
    data class Success(val event: List<Event>) : HomeEventUiState()
    object Error : HomeEventUiState()
    object Loading : HomeEventUiState()
}

class HomeEventViewModel(private val evt: EventRepository) : ViewModel() {
    var evtUIState: HomeEventUiState by mutableStateOf(HomeEventUiState.Loading)
        private set

    init {
        getEvt()
    }

    fun getEvt() {
        viewModelScope.launch {
            evtUIState = HomeEventUiState.Loading
            evtUIState = try {
                HomeEventUiState.Success(evt.getEvent().data)
            } catch (e: IOException) {
                HomeEventUiState.Error
            } catch (e: HttpException) {
                HomeEventUiState.Error
            }
        }
    }

    fun deleteEvt(id_event: Int) {
        viewModelScope.launch {
            try {
                evt.deleteEvent(id_event)
            } catch (e: IOException) {
                HomeEventUiState.Error
            } catch (e: HttpException) {
                HomeEventUiState.Error
            }
        }
    }
}