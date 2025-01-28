package com.example.a13finalucp.service

import com.example.a13finalucp.model.Peserta
import com.example.a13finalucp.model.PesertaResponse
import com.example.a13finalucp.model.PesertaResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PesertaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("peserta")
    suspend fun getPeserta(): PesertaResponse

    @GET("peserta/{id_peserta}")
    suspend fun getPesertaByIdPeserta(@Path("id_peserta") id_peserta: Int): PesertaResponseDetail

    @POST("peserta/store")
    suspend fun insertPeserta(@Body peserta: Peserta)

    @PUT("peserta/{id_peserta}")
    suspend fun updatePeserta(@Path("id_peserta") id_peserta: Int, @Body peserta: Peserta)

    @DELETE("peserta/{id_peserta}")
    suspend fun deletePeserta(@Path("id_peserta") id_peserta: Int): Response<Void>
}