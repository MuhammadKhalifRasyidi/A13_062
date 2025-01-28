package com.example.a13finalucp.ui.view.peserta

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
import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.peserta.HomePesertaUiState
import com.example.a13finalucp.ui.viewmodel.peserta.HomePesertaViewModel
import com.example.a13finalucp.ui.viewmodel.peserta.PenyediaPesertaViewModel

object DestinasiHomePeserta : DestinasiNavigasi {
    override val route = "homePeserta"
    override val titleRes = "Home Peserta"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePesertaScreen(
    navigateToItemInsert: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomePesertaViewModel = viewModel(factory = PenyediaPesertaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomePeserta.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPst()
                },
                navigateUp = onBackClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemInsert,
                shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Peserta")
            }
        },
    ) { innerPadding ->
        HomePesertaStatus(
            homePesertaUiState = viewModel.pstUIState,
            retryAction = { viewModel.getPst() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePst(it.id_peserta)
                viewModel . getPst ()
            }
        )
    }
}

@Composable
fun HomePesertaStatus(
    homePesertaUiState: HomePesertaUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Peserta) -> Unit = {},
    onDetailClick: (Int) -> Unit
) {


    when (homePesertaUiState) {
        is HomePesertaUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())


        is HomePesertaUiState.Success ->
            if (homePesertaUiState.peserta.isEmpty()) {
                return Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Peserta")
                }
            } else {
                PstLayout(
                    peserta = homePesertaUiState.peserta,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.id_peserta) },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomePesertaUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
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
fun PstLayout(
    peserta: List<Peserta>,
    modifier: Modifier = Modifier,
    onDetailClick: (Peserta) -> Unit,
    onDeleteClick: (Peserta) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(peserta) { IDPeserta ->
            PstCard(
                peserta = IDPeserta,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(IDPeserta) },
                onDeleteClick = {
                    onDeleteClick(IDPeserta)
                }
            )
        }
    }
}

@Composable
fun PstCard(
    peserta: Peserta,
    modifier: Modifier = Modifier,
    onDeleteClick: (Peserta) -> Unit = {}
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
                    text = peserta.nama_peserta,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(peserta) }) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                    )
                }
            }

            Text(
                text = "ID Peserta: ${peserta.id_peserta}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = peserta.email,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = peserta.nomor_telepon,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}