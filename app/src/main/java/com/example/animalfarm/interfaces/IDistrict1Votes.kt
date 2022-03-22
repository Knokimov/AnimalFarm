package com.example.animalfarm.interfaces

import com.example.animalfarm.models.PartyID
import retrofit2.http.GET

interface IDistrict1Votes {
    @GET("district1.json")
    suspend fun listVotes(): List<PartyID>
}