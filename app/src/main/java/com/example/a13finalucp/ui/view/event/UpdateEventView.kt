package com.example.a13finalucp.ui.view.event

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
import com.example.a13finalucp.ui.viewmodel.event.PenyediaEventViewModel
import com.example.a13finalucp.ui.viewmodel.event.UpdateEventViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object DestinasiUpdateEvent : DestinasiNavigasi {
    override val route = "updateEvent"
    override val titleRes = "Update Event"
    const val IDEvent = "id_event"
    val routesWithArg = "$route/{$IDEvent}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEventScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    viewModel: UpdateEventViewModel = viewModel(factory = PenyediaEventViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateEvent.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = onBack,
            )
        }
    ) { padding ->
        InsertBody(
            modifier = Modifier.padding(padding),
            insertEvtUiState = viewModel.UpdateEvtUiState,
            onEventValueChange = viewModel::UpdateInsertEvtState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateEvt()
                    delay(600)
                    withContext(Dispatchers.Main) {
                        onNavigate()
                    }
                }
            }
        )
    }
}