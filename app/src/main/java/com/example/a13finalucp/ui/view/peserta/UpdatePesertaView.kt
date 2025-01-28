package com.example.a13finalucp.ui.view.peserta

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
import com.example.a13finalucp.ui.viewmodel.peserta.PenyediaPesertaViewModel
import com.example.a13finalucp.ui.viewmodel.peserta.UpdatePesertaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdatePeserta : DestinasiNavigasi {
    override val route = "updatePeserta"
    override val titleRes = "Update Peserta"
    const val IDPeserta = "id_peserta"
    val routesWithArg = "$route/{$IDPeserta}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePesertaScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdatePesertaViewModel = viewModel(factory = PenyediaPesertaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdatePeserta.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBackClick,
            )
        }
    ) { padding ->
        InsertBody(
            modifier = Modifier.padding(padding),
            insertPstUiState = viewModel.UpdatePstUiState,
            onPesertaValueChange = viewModel::UpdateInsertPstState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePst()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}