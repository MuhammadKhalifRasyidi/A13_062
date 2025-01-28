package com.example.a13finalucp.model

import kotlinx.serialization.Serializable

@Serializable
data class Tiket (
    val id_tiket: Int = 0,
    val id_event: Int = 0,
    val id_peserta: Int = 0,
    val kapasitas_tiket: Int,
    val harga_tiket: Int
)

@Serializable
data class TiketResponse(
    val status: Boolean,
    val message: String,
    val data: List<Tiket>
)

@Serializable
data class TiketResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Tiket
)