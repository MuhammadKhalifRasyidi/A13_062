package com.example.a13finalucp.service

import com.example.a13finalucp.model.Event
import com.example.a13finalucp.model.EventResponse
import com.example.a13finalucp.model.EventResponseDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EventService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("event")
    suspend fun getEvent(): EventResponse

    @GET("event/{id_event}")
    suspend fun getEventByIdEvent(@Path("id_event") id_Event: Int): EventResponseDetail

    @POST("event/store")
    suspend fun insertEvent(@Body event: Event)

    @PUT("event/{id_event}")
    suspend fun updateEvent(@Path("id_event") id_Event: Int, @Body event: Event)

    @DELETE("event/{id_event}")
    suspend fun deleteEvent(@Path("id_event") id_Event: Int): Response<Void>
}