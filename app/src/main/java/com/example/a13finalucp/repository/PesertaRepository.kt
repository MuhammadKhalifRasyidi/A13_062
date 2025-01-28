package com.example.a13finalucp.repository

import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.model.PesertaResponse
import com.example.a13finalucp.model.PesertaResponseDetail
import com.example.a13finalucp.service.PesertaService
import java.io.IOException

interface PesertaRepository {
    suspend fun getPeserta(): PesertaResponse

    suspend fun insertPeserta(peserta: Peserta)

    suspend fun updatePeserta(id_peserta: Int, peserta: Peserta)

    suspend fun deletePeserta(id_peserta: Int)

    suspend fun getPesertaByIdPeserta(id_peserta: Int): PesertaResponseDetail
}

class NetworkPesertaRepository(
    private val pesertaApiService: PesertaService
) : PesertaRepository {
    override suspend fun insertPeserta(peserta: Peserta) {
        pesertaApiService.insertPeserta(peserta)
    }

    override suspend fun updatePeserta(id_peserta: Int, peserta: Peserta) {
        pesertaApiService.updatePeserta(id_peserta, peserta)
    }

    override suspend fun deletePeserta(id_peserta: Int) {
        try {
            val response = pesertaApiService.deletePeserta(id_peserta)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete peserta. HTTP Status code: " +
                        "${response.code()} ")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception) {
            throw e
        }
    }

    override suspend fun getPeserta(): PesertaResponse {
        return pesertaApiService.getPeserta()
    }

    override suspend fun getPesertaByIdPeserta(id_peserta: Int): PesertaResponseDetail {
        return pesertaApiService.getPesertaByIdPeserta(id_peserta)
    }
}