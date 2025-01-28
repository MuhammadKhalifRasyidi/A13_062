package com.example.a13finalucp.ui.view.transaksi

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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.model.DataForeignKey
import com.example.a13finalucp.model.DataForeignKey.TiketEvent
import com.example.a13finalucp.model.DataForeignKey.TiketPeserta
import com.example.a13finalucp.model.DataForeignKey.idTiket
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.view.peserta.DestinasiDetailPeserta
import com.example.a13finalucp.ui.viewmodel.transaksi.DetailTransaksiViewModel
import com.example.a13finalucp.ui.viewmodel.transaksi.PenyediaTransaksiViewModel

object DestinasiDetailTransaksi : DestinasiNavigasi {
    override val route = "detailTransaksi"
    override val titleRes = "Detail Transaksi"
    const val IDTransaksi = "id_transaksi"
    val routeWithArgs = "$route/{$IDTransaksi}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTransaksiScreen(
    id_transaksi: Int,
    onEditClick: (Int) -> Unit = { },
    onBackClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: DetailTransaksiViewModel = viewModel(factory = PenyediaTransaksiViewModel.Factory)
) {
    val transaksi = viewModel.uiState.detailTssUiTransaksi
    val NamaTiketEvent = TiketEvent()
    val IdTiketList = idTiket()
    val NamaTiketPeserta = TiketPeserta()

    val TiketEvent = NamaTiketEvent.find { it.first == transaksi.id_tiket }?.second ?: ""
    val IdTiket = IdTiketList.find { it == transaksi.id_tiket }?: ""
    val TiketPeserta = NamaTiketPeserta.find { it.first == transaksi.id_tiket }?.second ?: ""

    LaunchedEffect(id_transaksi) {
        viewModel.fetchDetailTransaksi(id_transaksi)
    }

    val isLoading = viewModel.uiState.isLoading
    val isError = viewModel.uiState.isError
    val errorMessage = viewModel.uiState.errorMessage

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailTransaksi.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.fetchDetailTransaksi(id_transaksi)
                },
                navigateUp = onBackClick
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
                else if (viewModel.uiState.isUiTransaksiNotEmpty) {
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
                                DetailTssRow(label = "ID Tiket", value = IdTiket)
                                DetailTssRow(label = "Event", value = TiketEvent)
                                DetailTssRow(label = "Peserta", value = TiketPeserta)
                                DetailTssRow(label = "Jumlah Tiket", value = transaksi.jumlah_tiket)
                                DetailTssRow(label = "Tanggal Transaksi", value = transaksi.tanggal_transaksi)
                                DetailTssRow(label = "Jumlah Pembayaran", value = transaksi.jumlah_pembayaran)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { onEditClick(transaksi.id_transaksi) }) {
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
fun DetailTssRow(label: String, value: Any) {
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