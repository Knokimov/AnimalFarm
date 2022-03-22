package com.example.animalfarm.interfaces

import com.example.animalfarm.models.PartyID
import retrofit2.http.GET

interface IDistrict2Votes {
    @GET("district2.json")
    suspend fun listVotes(): List<PartyID>
}