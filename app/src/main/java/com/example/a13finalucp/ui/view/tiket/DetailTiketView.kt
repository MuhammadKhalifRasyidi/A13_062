package com.example.a13finalucp.ui.view.tiket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.model.DataForeignKey
import com.example.a13finalucp.model.DataForeignKey.lokasiEvent
import com.example.a13finalucp.model.DataForeignKey.namaEvent
import com.example.a13finalucp.model.DataForeignKey.tanggalEvent
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.tiket.DetailTiketViewModel
import com.example.a13finalucp.ui.viewmodel.tiket.PenyediaTiketViewModel

object DestinasiDetailTiket : DestinasiNavigasi {
    override val route = "detailTiket"
    override val titleRes = "Detail Tiket"
    const val IDTiket = "id_tiket"
    val routeWithArgs = "$route/{$IDTiket}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTiketScreen(
    id_tiket: Int,
    onEditClick: (Int) -> Unit = { },
    onBackClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailTiketViewModel = viewModel(factory = PenyediaTiketViewModel.Factory)
) {
    val tiket = viewModel.uiState.detailTktUiTiket
    val EventList = namaEvent()
    val tglEventList = tanggalEvent()
    val lksEventList = lokasiEvent()
    val PesertaList = DataForeignKey.DataPeserta()

    val NamaEvent = EventList.find { it.first == tiket.id_event }?.second ?: ""
    val TanggalEvent = tglEventList.find { it.first == tiket.id_event }?.second ?: ""
    val LokasiEvent = lksEventList.find { it.first == tiket.id_event }?.second ?: ""
    val NamaPeserta = PesertaList.find { it.first == tiket.id_peserta }?.second ?: ""

    LaunchedEffect(id_tiket) {
        viewModel.fetchDetailTiket(id_tiket)
    }

    val isLoading = viewModel.uiState.isLoading
    val isError = viewModel.uiState.isError
    val errorMessage = viewModel.uiState.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(DestinasiDetailTiket.titleRes) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                else if (isError) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else if (viewModel.uiState.isUiTiketNotEmpty) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                // Use Row for each detail with label and value aligned
                                DetailTktRow(label = "ID Tiket", value = tiket.id_tiket)
                                DetailTktRow(label = "Nama Event", value = NamaEvent)
                                DetailTktRow(label = "Nama Peserta", value = NamaPeserta)
                                DetailTktRow(label = "Tanggal Event", value = TanggalEvent)
                                DetailTktRow(label = "Lokasi Event", value = LokasiEvent)
                                DetailTktRow(label = "Kapasitas Tiket", value = tiket.kapasitas_tiket)
                                DetailTktRow(label = "Harga Tiket", value = tiket.harga_tiket)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { onEditClick(tiket.id_tiket) }) {
                                Text("Edit Data")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DetailTktRow(label: String, value: Any) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ": $value",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(2f)
        )
    }
}

