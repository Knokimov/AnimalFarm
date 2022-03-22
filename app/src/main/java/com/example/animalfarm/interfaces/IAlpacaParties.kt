package com.example.animalfarm.interfaces

import com.example.animalfarm.models.Parties
import retrofit2.http.GET

interface IAlpacaParties {
    @GET("alpacaparties.json")
    suspend fun listAlpacas(): Parties
}