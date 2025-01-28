package com.example.a13finalucp.DI

import com.example.a13finalucp.repository.EventRepository
import com.example.a13finalucp.repository.NetworkEventRepository
import com.example.a13finalucp.repository.NetworkPesertaRepository
import com.example.a13finalucp.repository.NetworkTiketRepository
import com.example.a13finalucp.repository.NetworkTransaksiRepository
import com.example.a13finalucp.repository.PesertaRepository
import com.example.a13finalucp.repository.TiketRepository
import com.example.a13finalucp.repository.TransaksiRepository
import com.example.a13finalucp.service.EventService
import com.example.a13finalucp.service.PesertaService
import com.example.a13finalucp.service.TiketService
import com.example.a13finalucp.service.TransaksiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pesertaRepository: PesertaRepository
    val eventRepository: EventRepository
    val tiketRepository: TiketRepository
}

class  ContainerApplication : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/api/" //localhost diganti ip kalo run di hp
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

//    PESERTA
    private val pesertaService: PesertaService by lazy{
        retrofit.create(PesertaService::class.java) }

    override val pesertaRepository: PesertaRepository by lazy {
        NetworkPesertaRepository(pesertaService)
    }

//    EVENT
    private val eventService: EventService by lazy{
        retrofit.create(EventService::class.java) }

    override val eventRepository: EventRepository by lazy {
        NetworkEventRepository(eventService)
    }

//    TIKET
    private val tiketService: TiketService by lazy{
        retrofit.create(TiketService::class.java) }

    override val tiketRepository: TiketRepository by lazy {
        NetworkTiketRepository(tiketService)
    }

//    TRANSAKSI

}