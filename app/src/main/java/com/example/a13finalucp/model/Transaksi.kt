package com.example.a13finalucp.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaksi (
    val id_transaksi: Int = 0,
    val id_tiket: Int = 0,
    val jumlah_tiket: Int,
    val jumlah_pembayaran: Int,
    val tanggal_transaksi: String
)

@Serializable
data class TransaksiResponse(
    val status: Boolean,
    val message: String,
    val data: List<Transaksi>
)

@Serializable
data class TransaksiResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Transaksi
)