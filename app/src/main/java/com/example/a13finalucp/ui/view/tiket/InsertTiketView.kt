package com.example.a13finalucp.ui.view.tiket

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
import com.example.a13finalucp.model.DataForeignKey
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.customwidget.Dropdown
import com.example.a13finalucp.ui.customwidget.DropdownOption
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.tiket.InsertTiketViewModel
import com.example.a13finalucp.ui.viewmodel.tiket.InsertTktUiState
import com.example.a13finalucp.ui.viewmodel.tiket.InsertTktUiTiket
import com.example.a13finalucp.ui.viewmodel.tiket.PenyediaTiketViewModel
import kotlinx.coroutines.launch

object DestinasiInsertTiket : DestinasiNavigasi {
    override val route = "insertTiket"
    override val titleRes = "Insert Tiket"
}



@Composable
fun InsertBody(
    insertTktUiState: InsertTktUiState,
    onTiketValueChange: (InsertTktUiTiket) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertTktUiTiket = insertTktUiState.insertTktUiTiket,
            onValueChange = onTiketValueChange,
            modifier = Modifier.fillMaxWidth(),
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
    insertTktUiTiket: InsertTktUiTiket,
    modifier: Modifier = Modifier,
    onValueChange: (InsertTktUiTiket) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertTktUiTiket.id_tiket.toString(),
            onValueChange = {
                val IDTiket = it.toIntOrNull()
                if (IDTiket != null) {
                    onValueChange(insertTktUiTiket.copy(id_tiket = IDTiket))
                }
            },
            label = { Text("ID Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Dropdown untuk ID Event
        Dropdown(
            selectedValue = DataForeignKey.namaEvent().find { it.first == insertTktUiTiket.id_event }?.second ?: "",
            options = DataForeignKey.namaEvent().map { DropdownOption(it.first, it.second) },
            label = "Nama Event",
            onValueChangedDropdown = { selectedEvent ->
                onValueChange(insertTktUiTiket.copy(
                    id_event = selectedEvent
                ))
            },
        )

        // Dropdown untuk ID Peserta
        Dropdown(
            selectedValue = DataForeignKey.DataPeserta().find { it.first == insertTktUiTiket.id_peserta }?.second ?: "",
            options = DataForeignKey.DataPeserta().map { DropdownOption(it.first, it.second) },
            label = "Nama Peserta",
            onValueChangedDropdown = { selectedPeserta ->
                onValueChange(insertTktUiTiket.copy(
                    id_peserta = selectedPeserta
                ))
            },
        )

        // Kapasitas Tiket
        OutlinedTextField(
            value = insertTktUiTiket.kapasitas_tiket.toString(),
            onValueChange = { it.toIntOrNull()?. let{ kapasitasTiket ->
            onValueChange(insertTktUiTiket.copy(kapasitas_tiket = kapasitasTiket))
            }},
            label = { Text("Kapasitas Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Harga Tiket
        OutlinedTextField(
            value = insertTktUiTiket.harga_tiket.toString(),
            onValueChange = { it.toIntOrNull()?. let { hargaTiket ->
                onValueChange(insertTktUiTiket.copy(harga_tiket = hargaTiket)) }},
            label = { Text("Harga Tiket") },
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