package com.example.a13finalucp.ui.view.transaksi

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.transaksi.PenyediaTransaksiViewModel
import com.example.a13finalucp.ui.viewmodel.transaksi.UpdateTransaksiViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateTransaksi : DestinasiNavigasi {
    override val route = "updateTransaksi"
    override val titleRes = "Update Transaksi"
    const val IDTransaksi = "id_transaksi"
    val routesWithArg = "$route/{$IDTransaksi}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTransaksiScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateTransaksiViewModel = viewModel(factory = PenyediaTransaksiViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateTransaksi.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        InsertBody(
            modifier = Modifier.padding(padding),
            insertTssUiState = viewModel.UpdateTssUiState,
            onTransaksiValueChange = viewModel::UpdateInsertTssState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTss()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}