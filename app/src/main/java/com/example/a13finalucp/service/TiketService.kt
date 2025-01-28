package com.example.a13finalucp.service

import com.example.a13finalucp.model.Tiket
import com.example.a13finalucp.model.TiketResponse
import com.example.a13finalucp.model.TiketResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TiketService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("tiket")
    suspend fun getTiket(): TiketResponse

    @GET("tiket/{id_tiket}")
    suspend fun getTiketByIdTiket(@Path("id_tiket") id_Tiket: Int): TiketResponseDetail

    @POST("tiket/store")
    suspend fun insertTiket(@Body tiket: Tiket)

    @PUT("tiket/{id_tiket}")
    suspend fun updateTiket(@Path("id_tiket") id_Tiket: Int, @Body tiket: Tiket)

    @DELETE("tiket/{id_tiket}")
    suspend fun deleteTiket(@Path("id_tiket") id_Tiket: Int): Response<Void>
}