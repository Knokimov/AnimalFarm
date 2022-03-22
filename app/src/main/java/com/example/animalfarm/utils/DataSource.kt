package com.example.animalfarm.utils

import com.example.animalfarm.interfaces.IAlpacaParties
import com.example.animalfarm.interfaces.IDistrict1Votes
import com.example.animalfarm.interfaces.IDistrict2Votes
import com.example.animalfarm.interfaces.IDistrict3Votes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

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

    val alpacaParties: IAlpacaParties = dataJson.create(IAlpacaParties::class.java)
    val district1Votes: IDistrict1Votes = dataJson.create(IDistrict1Votes::class.java)
    val district2Votes: IDistrict2Votes = dataJson.create(IDistrict2Votes::class.java)
    val district3Votes: IDistrict3Votes = dataXML.create(IDistrict3Votes::class.java)
}