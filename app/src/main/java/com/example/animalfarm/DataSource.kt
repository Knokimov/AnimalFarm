package com.example.animalfarm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

class DataSource {
    private val dataJson by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v22/obligatoriske-oppgaver/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val dataXML by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.uio.no/studier/emner/matnat/ifi/IN2000/v22/obligatoriske-oppgaver/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    val alpacaparties: AlpacapartiesRest = dataJson.create(AlpacapartiesRest::class.java)
    val district1: District1Rest = dataJson.create(District1Rest::class.java)
    val district2: District2Rest = dataJson.create(District2Rest::class.java)
    val district3: District3Rest = dataXML.create(District3Rest::class.java)
}

interface AlpacapartiesRest {
    @GET("alpacaparties.json")
    suspend fun listAlpacas(): Parties
}

interface District1Rest {
    @GET("district1.json")
    suspend fun listVotes(): ArrayList<ID>
}

interface District2Rest {
    @GET("district2.json")
    suspend fun listVotes(): List<ID>
}
interface District3Rest {
    @GET("district3.xml")
    suspend fun getFeed(): LinkResponse
}