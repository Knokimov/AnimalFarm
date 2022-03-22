package com.example.animalfarm.models

import com.google.gson.annotations.SerializedName

data class Parties(
    @SerializedName("parties" ) var parties: MutableList<AlpacaParty> = mutableListOf()
)

data class AlpacaParty(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("leader") var leader: String? = null,
    @SerializedName("img") var img: String? = null,
    @SerializedName("color") var color: String? = null,
) {
    var votes: String? = ""
}