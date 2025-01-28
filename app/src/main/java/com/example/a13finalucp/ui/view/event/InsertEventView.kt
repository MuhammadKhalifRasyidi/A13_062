package com.example.a13finalucp.ui.view.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.event.InsertEventViewModel
import com.example.a13finalucp.ui.viewmodel.event.InsertEvtUiEvent
import com.example.a13finalucp.ui.viewmodel.event.InsertEvtUiState
import com.example.a13finalucp.ui.viewmodel.event.PenyediaEventViewModel
import kotlinx.coroutines.launch

object DestinasiInsertEvent : DestinasiNavigasi {
    override val route = "insertEvent"
    override val titleRes = "Insert Event"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertEvtScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertEvent.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        InsertBody(
            insertEvtUiState = viewModel.uiState,
            onEventValueChange = viewModel::UpdateInsertEvtState, onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertEvt()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InsertBody(
    insertEvtUiState: InsertEvtUiState,
    onEventValueChange: (InsertEvtUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp), modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertEvtUiEvent = insertEvtUiState.insertEvtUiEvent,
            onValueChange = onEventValueChange,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
fun FormInput(
    insertEvtUiEvent: InsertEvtUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertEvtUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertEvtUiEvent.nama_event,
            onValueChange = { onValueChange(insertEvtUiEvent.copy(nama_event = it)) },
            label = { Text("Nama Event") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertEvtUiEvent.id_event?.toString() ?: "",
            onValueChange = {
                val IDEvent = it.toIntOrNull()
                if (IDEvent != null) {
                    onValueChange(insertEvtUiEvent.copy(id_event = IDEvent))
                }
            },
            label = { Text("ID Event") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertEvtUiEvent.deskripsi_event,
            onValueChange = { onValueChange(insertEvtUiEvent.copy(deskripsi_event = it)) },
            label = { Text("Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertEvtUiEvent.tanggal_event,
            onValueChange = { onValueChange(insertEvtUiEvent.copy(tanggal_event = it)) },
            label = { Text("Tanggal Event") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertEvtUiEvent.lokasi_event,
            onValueChange = { onValueChange(insertEvtUiEvent.copy(lokasi_event = it)) },
            label = { Text("Lokasi Event") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}