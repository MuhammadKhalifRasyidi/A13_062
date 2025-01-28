package com.example.a13finalucp.repository

import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.model.PesertaResponse
import com.example.a13finalucp.model.PesertaResponseDetail
import com.example.a13finalucp.model.Transaksi
import com.example.a13finalucp.model.TransaksiResponse
import com.example.a13finalucp.model.TransaksiResponseDetail
import com.example.a13finalucp.service.PesertaService
import com.example.a13finalucp.service.TransaksiService
import java.io.IOException

interface TransaksiRepository {
    suspend fun getTransaksi(): TransaksiResponse

    suspend fun insertTransaksi(transaksi: Transaksi)

    suspend fun updateTransaksi(id_transaksi: Int, transaksi: Transaksi)

    suspend fun deleteTransaksi(id_transaksi: Int)

    suspend fun getTransaksiByIdTransaksi(id_transaksi: Int): TransaksiResponseDetail
}

class NetworkTransaksiRepository(
    private val transaksiApiService: TransaksiService
) : TransaksiRepository {
    override suspend fun insertTransaksi(transaksi: Transaksi) {
        transaksiApiService.insertTransaksi(transaksi)
    }

    override suspend fun updateTransaksi(id_transaksi: Int, transaksi: Transaksi) {
        transaksiApiService.updateTransaksi(id_transaksi, transaksi)
    }

    override suspend fun deleteTransaksi(id_transaksi: Int) {
        try {
            val response = transaksiApiService.deleteTransaksi(id_transaksi)
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

    override suspend fun getTransaksi(): TransaksiResponse {
        return transaksiApiService.getTransaksi()
    }

    override suspend fun getTransaksiByIdTransaksi(id_transaksi: Int): TransaksiResponseDetail {
        return transaksiApiService.getTransaksiByIdTransaksi(id_transaksi)
    }
}