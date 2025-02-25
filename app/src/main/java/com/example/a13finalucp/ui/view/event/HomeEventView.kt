package com.example.a13finalucp.ui.view.event

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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.R
import com.example.a13finalucp.model.Event
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.event.HomeEventUiState
import com.example.a13finalucp.ui.viewmodel.event.HomeEventViewModel
import com.example.a13finalucp.ui.viewmodel.event.PenyediaEventViewModel

object DestinasiHomeEvent : DestinasiNavigasi {
    override val route = "homeEvent"
    override val titleRes = "Home Event"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeEventScreen(
    navigateToItemInsert: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeEvent.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getEvt()
                },
                navigateUp = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemInsert,
                shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(bottom = 0.dp, start = 5.dp, end = 18.dp )
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
            }
        },
    ) { innerPadding ->
        HomeEventStatus(
            homeEventUiState = viewModel.evtUIState,
            retryAction = { viewModel.getEvt() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteEvt(it.id_event)
                viewModel . getEvt ()
            }
        )
    }
}

@Composable
fun HomeEventStatus(
    homeEventUiState: HomeEventUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Event) -> Unit = {},
    onDetailClick: (Int) -> Unit
) {


    when (homeEventUiState) {
        is HomeEventUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())


        is HomeEventUiState.Success ->
            if (homeEventUiState.event.isEmpty()) {
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Event")
                }
            } else {
                EvtLayout(
                    event = homeEventUiState.event,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.id_event) },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeEventUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.load),
        contentDescription = stringResource(R.string.app_name)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = ""
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
fun EvtLayout(
    event: List<Event>,
    modifier: Modifier = Modifier,
    onDetailClick: (Event) -> Unit,
    onDeleteClick: (Event) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(event) { IDEvent ->
            EvtCard(
                event = IDEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(IDEvent) },
                onDeleteClick = {
                    onDeleteClick(IDEvent)
                }
            )
        }
    }
}

@Composable
fun EvtCard(
    event: Event,
    modifier: Modifier = Modifier,
    onDeleteClick: (Event) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                    text = event.nama_event,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(event) }) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                    )
                }
            }

            Text(
                text = "ID Event: ${event.id_event}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = event.deskripsi_event,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = event.tanggal_event.toString(),
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = event.lokasi_event,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}