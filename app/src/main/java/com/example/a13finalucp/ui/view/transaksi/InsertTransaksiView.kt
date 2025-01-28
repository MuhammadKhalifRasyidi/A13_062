package com.example.a13finalucp.ui.view.transaksi

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
import com.example.a13finalucp.ui.viewmodel.transaksi.InsertTransaksiViewModel
import com.example.a13finalucp.ui.viewmodel.transaksi.InsertTssUiState
import com.example.a13finalucp.ui.viewmodel.transaksi.InsertTssUiTransaksi
import com.example.a13finalucp.ui.viewmodel.transaksi.PenyediaTransaksiViewModel
import kotlinx.coroutines.launch

object DestinasiInsertTransaksi : DestinasiNavigasi {
    override val route = "insertTransaksi"
    override val titleRes = "Insert Transaksi"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertTssScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertTransaksiViewModel = viewModel(factory = PenyediaTransaksiViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertTransaksi.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        InsertBody(
            insertTssUiState = viewModel.uiState,
            onTransaksiValueChange = viewModel::UpdateInsertTssState, onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertTss()
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
    insertTssUiState: InsertTssUiState,
    onTransaksiValueChange: (InsertTssUiTransaksi) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp), modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertTssUiTransaksi = insertTssUiState.insertTssUiTransaksi,
            onValueChange = onTransaksiValueChange,
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
    insertTssUiTransaksi: InsertTssUiTransaksi,
    modifier: Modifier = Modifier,
    onValueChange: (InsertTssUiTransaksi) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertTssUiTransaksi.id_transaksi.toString(),
            onValueChange = {
                val IDTransaksi = it.toIntOrNull()
                if (IDTransaksi != null) {
                    onValueChange(insertTssUiTransaksi.copy(id_transaksi = IDTransaksi))
                }
            },
            label = { Text("ID Transaksi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Dropdown untuk ID Tiket
        Dropdown(
            selectedValue = DataForeignKey.idTiket().find { it == insertTssUiTransaksi.id_tiket }?.toString() ?: "",
            options = DataForeignKey.idTiket().map { DropdownOption(it, it.toString()) },
            label = "ID Tiket",
            onValueChangedDropdown = { selectedIDTiket ->
                onValueChange(insertTssUiTransaksi.copy(
                    id_tiket = selectedIDTiket
                ))
            },
        )

        OutlinedTextField(
            value = insertTssUiTransaksi.jumlah_tiket.toString(),
            onValueChange = {
                val JumlahTiket = it.toIntOrNull()
                if (JumlahTiket != null) {
                    onValueChange(insertTssUiTransaksi.copy(jumlah_tiket = JumlahTiket))
                }
            },
            label = { Text("Jumlah Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertTssUiTransaksi.tanggal_transaksi,
            onValueChange = { onValueChange(insertTssUiTransaksi.copy(tanggal_transaksi = it)) },
            label = { Text("Tanggal Transaksi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = insertTssUiTransaksi.jumlah_pembayaran.toString(),
            onValueChange = { },
            label = { Text("Total Pembayaran") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
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
