package com.example.a13finalucp.ui.view.peserta

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
import com.example.a13finalucp.ui.viewmodel.peserta.InsertPesertaViewModel
import com.example.a13finalucp.ui.viewmodel.peserta.InsertPstUiPeserta
import com.example.a13finalucp.ui.viewmodel.peserta.InsertPstUiState
import com.example.a13finalucp.ui.viewmodel.peserta.PenyediaPesertaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertPeserta : DestinasiNavigasi {
    override val route = "insert"
    override val titleRes = "Insert Peserta"
}



@Composable
fun InsertBody(
    insertPstUiState: InsertPstUiState,
    onPesertaValueChange: (InsertPstUiPeserta) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp), modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertPstUiPeserta = insertPstUiState.insertPstUiPeserta,
            onValueChange = onPesertaValueChange,
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
    insertPstUiPeserta: InsertPstUiPeserta,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPstUiPeserta) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertPstUiPeserta.nama_peserta,
            onValueChange = { onValueChange(insertPstUiPeserta.copy(nama_peserta = it)) },
            label = { Text("Nama Peserta") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertPstUiPeserta.id_peserta?.toString() ?: "",
            onValueChange = {
                val IDPeserta = it.toIntOrNull()
                if (IDPeserta != null) {
                    onValueChange(insertPstUiPeserta.copy(id_peserta = IDPeserta))
                }
            },
            label = { Text("ID Peserta") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertPstUiPeserta.email,
            onValueChange = { onValueChange(insertPstUiPeserta.copy(email = it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertPstUiPeserta.nomor_telepon,
            onValueChange = { onValueChange(insertPstUiPeserta.copy(nomor_telepon = it)) },
            label = { Text("Nomor Telepon") },
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