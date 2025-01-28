package com.example.a13finalucp.repository

import com.example.a13finalucp.model.Event
import com.example.a13finalucp.model.EventResponse
import com.example.a13finalucp.model.EventResponseDetail
import com.example.a13finalucp.service.EventService
import java.io.IOException


interface EventRepository {
    suspend fun getEvent(): EventResponse

    suspend fun insertEvent(event: Event)

    suspend fun updateEvent(id_event: Int, event: Event)

    suspend fun deleteEvent(id_event: Int)

    suspend fun getEventByIdEvent(id_event: Int): EventResponseDetail
}

class NetworkEventRepository(
    private val eventApiService: EventService
) : EventRepository {
    override suspend fun insertEvent(event: Event) {
        eventApiService.insertEvent(event)
    }

    override suspend fun updateEvent(id_event: Int, event: Event) {
        eventApiService.updateEvent(id_event, event)
    }

    override suspend fun deleteEvent(id_event: Int) {
        try {
            val response = eventApiService.deleteEvent(id_event)
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

    override suspend fun getEvent(): EventResponse {
        return eventApiService.getEvent()
    }

    override suspend fun getEventByIdEvent(id_event: Int): EventResponseDetail {
        return eventApiService.getEventByIdEvent(id_event)
    }
}