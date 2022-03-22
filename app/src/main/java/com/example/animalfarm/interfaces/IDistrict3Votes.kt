package com.example.animalfarm.interfaces

import com.example.animalfarm.models.LinkResponse
import retrofit2.http.GET

interface IDistrict3Votes {
    @GET("district3.xml")
    suspend fun listVotes(): LinkResponse
}