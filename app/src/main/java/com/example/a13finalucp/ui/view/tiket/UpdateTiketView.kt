package com.example.a13finalucp.ui.view.tiket

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
import com.example.a13finalucp.ui.viewmodel.tiket.PenyediaTiketViewModel
import com.example.a13finalucp.ui.viewmodel.tiket.UpdateTiketViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateTiket : DestinasiNavigasi {
    override val route = "updateTiket"
    override val titleRes = "Update Tiket"
    const val IDTiket = "id_tiket"
    val routesWithArg = "$route/{$IDTiket}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTiketScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateTiketViewModel = viewModel(factory = PenyediaTiketViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateTiket.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        InsertBody(
            modifier = Modifier.padding(padding),
            insertTktUiState = viewModel.UpdateTktUiState,
            onTiketValueChange = viewModel::UpdateInsertTktState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTkt()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}