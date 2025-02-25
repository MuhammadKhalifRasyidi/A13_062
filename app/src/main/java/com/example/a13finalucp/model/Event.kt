package com.example.a13finalucp.model

import kotlinx.serialization.Serializable

@Serializable
data class Event (
    val id_event: Int = 0,
    val nama_event: String,
    val deskripsi_event: String,
    val tanggal_event: String,
    val lokasi_event: String,
)

@Serializable
data class EventResponse(
    val status: Boolean,
    val message: String,
    val data: List<Event>
)

@Serializable
data class EventResponseDetail(
    val status: Boolean,
    val message: String,
    val data: Event
)