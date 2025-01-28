package com.example.a13finalucp.ui.view.tiket

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.R
import com.example.a13finalucp.model.DataForeignKey.DataPeserta
import com.example.a13finalucp.model.DataForeignKey.lokasiEvent
import com.example.a13finalucp.model.DataForeignKey.namaEvent
import com.example.a13finalucp.model.DataForeignKey.tanggalEvent
import com.example.a13finalucp.model.Tiket
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.tiket.HomeTiketUiState
import com.example.a13finalucp.ui.viewmodel.tiket.HomeTiketViewModel
import com.example.a13finalucp.ui.viewmodel.tiket.PenyediaTiketViewModel

object DestinasiHomeTiket : DestinasiNavigasi {
    override val route = "homeTiket"
    override val titleRes = "Home Tiket"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTiketScreen(
    navigateToItemInsert: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeTiketViewModel = viewModel(factory = PenyediaTiketViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeTiket.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getTkt()
                },
                navigateUp = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemInsert,
                shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Tiket")
            }
        },
    ) { innerPadding ->
        HomeTiketStatus(
            homeTiketUiState = viewModel.tktUIState,
            retryAction = { viewModel.getTkt() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteTkt(it.id_tiket)
                viewModel . getTkt ()
            }
        )
    }
}

@Composable
fun HomeTiketStatus(
    homeTiketUiState: HomeTiketUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tiket) -> Unit = {},
    onDetailClick: (Int) -> Unit
) {


    when (homeTiketUiState) {
        is HomeTiketUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())


        is HomeTiketUiState.Success ->
            if (homeTiketUiState.tiket.isEmpty()) {
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Tiket")
                }
            } else {
                TktLayout(
                    tiket = homeTiketUiState.tiket,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.id_tiket) },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeTiketUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = stringResource(R.string.app_name)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = ""
        )
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(16.dp)
        )
        Button (onClick = retryAction) {
            Text(stringResource(R.string.app_name))
        }
    }
}

@Composable
fun TktLayout(
    tiket: List<Tiket>,
    modifier: Modifier = Modifier,
    onDetailClick: (Tiket) -> Unit,
    onDeleteClick: (Tiket) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tiket) { IDTiket ->
            TktCard(
                tiket = IDTiket,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(IDTiket) },
                onDeleteClick = {
                    onDeleteClick(IDTiket)
                }
            )
        }
    }
}

@Composable
fun TktCard(
    tiket: Tiket,
    modifier: Modifier = Modifier,
    onDeleteClick: (Tiket) -> Unit = {}
) {
    val EventList = namaEvent()
    val tglEventList = tanggalEvent()
    val lksEventList = lokasiEvent()
    val PesertaList = DataPeserta()

    val NamaEvent = EventList.find { it.first == tiket.id_event }?.second ?: ""
    val TanggalEvent = tglEventList.find { it.first == tiket.id_event }?.second ?: ""
    val LokasiEvent = lksEventList.find { it.first == tiket.id_event }?.second ?: ""
    val NamaPeserta = PesertaList.find { it.first == tiket.id_peserta }?.second ?: ""

    val backgroundColor = when {
        tiket.kapasitas_tiket == 0 -> Color.Red.copy(alpha = 0.2f) // abu-abu
        tiket.kapasitas_tiket in 1..50 -> Color.Yellow.copy(alpha = 0.2f) // merah
        else -> Color.Green.copy(alpha = 0.2f) // hijau

    }

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tiket.id_tiket.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(tiket) }) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                    )
                }
            }

            Text(
                text = "Event: ${NamaEvent}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Peserta: ${NamaPeserta}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Tanggal Event: ${TanggalEvent}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Lokasi Event: ${LokasiEvent}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}