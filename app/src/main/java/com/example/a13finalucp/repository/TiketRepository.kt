package com.example.a13finalucp.repository

import com.example.a13finalucp.model.Tiket
import com.example.a13finalucp.model.TiketResponse
import com.example.a13finalucp.model.TiketResponseDetail
import com.example.a13finalucp.service.TiketService
import java.io.IOException

interface TiketRepository {
    suspend fun getTiket(): TiketResponse

    suspend fun insertTiket(tiket: Tiket)

    suspend fun updateTiket(id_tiket: Int, tiket: Tiket)

    suspend fun deleteTiket(id_tiket: Int)

    suspend fun getTiketByIdTiket(id_tiket: Int): TiketResponseDetail
}

class NetworkTiketRepository(
    private val tiketApiService: TiketService
) : TiketRepository {
    override suspend fun insertTiket(tiket: Tiket) {
        tiketApiService.insertTiket(tiket)
    }

    override suspend fun updateTiket(id_tiket: Int, tiket: Tiket) {
        tiketApiService.updateTiket(id_tiket, tiket)
    }

    override suspend fun deleteTiket(id_tiket: Int) {
        try {
            val response = tiketApiService.deleteTiket(id_tiket)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete peserta. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getTiket(): TiketResponse {
        return tiketApiService.getTiket()
    }

    override suspend fun getTiketByIdTiket(id_tiket: Int): TiketResponseDetail {
        return tiketApiService.getTiketByIdTiket(id_tiket)
    }
}