package com.example.a13finalucp.model

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.ui.viewmodel.event.HomeEventUiState
import com.example.a13finalucp.ui.viewmodel.event.HomeEventViewModel
import com.example.a13finalucp.ui.viewmodel.event.PenyediaEventViewModel
import com.example.a13finalucp.ui.viewmodel.peserta.HomePesertaUiState
import com.example.a13finalucp.ui.viewmodel.peserta.HomePesertaViewModel
import com.example.a13finalucp.ui.viewmodel.peserta.PenyediaPesertaViewModel
import com.example.a13finalucp.ui.viewmodel.tiket.HomeTiketUiState
import com.example.a13finalucp.ui.viewmodel.tiket.HomeTiketViewModel
import com.example.a13finalucp.ui.viewmodel.tiket.PenyediaTiketViewModel

object DataForeignKey {
//    EVENT
    @Composable
    fun namaEvent(
        dataEvent: HomeEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory)
    ): List<Pair<Int, String>> {
        val evtState = dataEvent.evtUIState

        return when (evtState) {
            is HomeEventUiState.Success -> {
                evtState.event.map { it.id_event to it.nama_event }
            }

            else -> {
                emptyList()
            }
        }
    }

    @Composable
    fun tanggalEvent(
        dataEvent: HomeEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory)
    ): List<Pair<Int, String>> {
        val evtState = dataEvent.evtUIState

        return when (evtState) {
            is HomeEventUiState.Success -> {
                evtState.event.map { it.id_event to it.tanggal_event }
            }

            else -> {
                emptyList()
            }
        }
    }

    @Composable
    fun lokasiEvent(
        dataEvent: HomeEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory)
    ): List<Pair<Int, String>> {
        val evtState = dataEvent.evtUIState

        return when (evtState) {
            is HomeEventUiState.Success -> {
                evtState.event.map { it.id_event to it.lokasi_event }
            }

            else -> {
                emptyList()
            }
        }
    }

//    PESERTA
    @Composable
    fun DataPeserta(
        dataPeserta: HomePesertaViewModel = viewModel(factory = PenyediaPesertaViewModel.Factory)
    ): List<Pair<Int, String>> {
        val pstState = dataPeserta.pstUIState

        return when (pstState) {
            is HomePesertaUiState.Success -> {
                pstState.peserta.map { it.id_peserta to it.nama_peserta }
            }

            else -> {
                emptyList()
            }
        }
    }

//    TIKET
    @Composable
    fun idTiket(
        dataTiket: HomeTiketViewModel = viewModel(factory = PenyediaTiketViewModel.Factory)
    ): List<Int> {
        val tktState = dataTiket.tktUIState

        return when (tktState) {
            is HomeTiketUiState.Success -> {
                tktState.tiket.map { it.id_tiket }
            }

            else -> {
                emptyList()
            }
        }
    }

    @Composable
    fun TiketEvent(
        dataTiket: HomeTiketViewModel = viewModel(factory = PenyediaTiketViewModel.Factory),
        dataEvent: HomeEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory),
    ): List<Pair<Int, String>> {
        val tktState = dataTiket.tktUIState
        val evtState = dataEvent.evtUIState

        return when (tktState) {
            is HomeTiketUiState.Success -> {
                when (evtState) {
                    is HomeEventUiState.Success -> {
                        // Map tiket data and find corresponding event names
                        tktState.tiket.map { tiket ->
                            val namaEvent = evtState.event.find { it.id_event == tiket.id_event }?.nama_event ?: "Event Tidak Ditemukan"
                            tiket.id_tiket to namaEvent
                        }
                    }
                    else -> {
                        // If event data is not loaded, return a default state
                        tktState.tiket.map { it.id_tiket to "Event Tidak Ditemukan" }
                    }
                }
            }
            else -> {
                emptyList()
            }
        }
    }

    @Composable
    fun TiketPeserta(
        dataTiket: HomeTiketViewModel = viewModel(factory = PenyediaTiketViewModel.Factory),
        dataPeserta: HomePesertaViewModel = viewModel(factory = PenyediaPesertaViewModel.Factory)
    ): List<Pair<Int, String>> {
        val tktState = dataTiket.tktUIState
        val pstState = dataPeserta.pstUIState

        return when (tktState) {
            is HomeTiketUiState.Success -> {
                when (pstState) {
                    is HomePesertaUiState.Success -> {
                        // Map tiket data and find corresponding event names
                        tktState.tiket.map { tiket ->
                            val namaPeserta = pstState.peserta.find { it.id_peserta == tiket.id_peserta }?.nama_peserta ?: "Peserta Tidak Ditemukan"
                            tiket.id_tiket to namaPeserta
                        }
                    }
                    else -> {
                        // If event data is not loaded, return a default state
                        tktState.tiket.map { it.id_tiket to "Peserta Tidak Ditemukan" }
                    }
                }
            }
            else -> {
                emptyList()
            }
        }
    }
}