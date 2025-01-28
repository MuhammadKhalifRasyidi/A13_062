package com.example.a13finalucp.ui.view.transaksi

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
import com.example.a13finalucp.model.DataForeignKey
import com.example.a13finalucp.model.DataForeignKey.TiketEvent
import com.example.a13finalucp.model.DataForeignKey.TiketPeserta
import com.example.a13finalucp.model.DataForeignKey.lokasiEvent
import com.example.a13finalucp.model.DataForeignKey.namaEvent
import com.example.a13finalucp.model.DataForeignKey.tanggalEvent
import com.example.a13finalucp.model.Transaksi
import com.example.a13finalucp.ui.customwidget.CostumeTopAppBar
import com.example.a13finalucp.ui.navigation.DestinasiNavigasi
import com.example.a13finalucp.ui.viewmodel.transaksi.HomeTransaksiUiState
import com.example.a13finalucp.ui.viewmodel.transaksi.HomeTransaksiViewModel
import com.example.a13finalucp.ui.viewmodel.transaksi.PenyediaTransaksiViewModel

object DestinasiHomeTransaksi : DestinasiNavigasi {
    override val route = "homeTransaksi"
    override val titleRes = "Home Transaksi"
}



@Composable
fun TssCard(
    transaksi: Transaksi,
    modifier: Modifier = Modifier,
    onDeleteClick: (Transaksi) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        val NamaTiketEvent = TiketEvent()
        val NamaTiketPeserta = TiketPeserta()

        val TiketEvent = NamaTiketEvent.find { it.first == transaksi.id_tiket }?.second ?: ""
        val TiketPeserta = NamaTiketPeserta.find { it.first == transaksi.id_tiket }?.second ?: ""

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = transaksi.id_tiket.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(transaksi) }) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                    )
                }
            }

            Text(
                text = "Event: ${TiketEvent}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Peserta: ${TiketPeserta}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = transaksi.tanggal_transaksi,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = transaksi.jumlah_pembayaran.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}