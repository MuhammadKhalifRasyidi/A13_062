package com.example.a13finalucp.service

import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.model.PesertaResponse
import com.example.a13finalucp.model.PesertaResponseDetail
import com.example.a13finalucp.model.Transaksi
import com.example.a13finalucp.model.TransaksiResponse
import com.example.a13finalucp.model.TransaksiResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TransaksiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("transaksi")
    suspend fun getTransaksi(): TransaksiResponse

    @GET("transaksi/{id_transaksi}")
    suspend fun getTransaksiByIdTransaksi(@Path("id_transaksi") id_transaksi: Int): TransaksiResponseDetail

    @POST("transaksi/store")
    suspend fun insertTransaksi(@Body transaksi: Transaksi)

    @PUT("transaksi/{id_transaksi}")
    suspend fun updateTransaksi(@Path("id_transaksi") id_transaksi: Int, @Body transaksi: Transaksi)

    @DELETE("transaksi/{id_transaksi}")
    suspend fun deleteTransaksi(@Path("id_transaksi") id_transaksi: Int): Response<Void>
}