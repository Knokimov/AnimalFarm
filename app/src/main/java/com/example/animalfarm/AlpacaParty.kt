package com.example.animalfarm

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class Parties(
    @SerializedName("parties" ) var parties : MutableList<AlpacaParty> = mutableListOf()
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

data class ID(
    @SerializedName("id") var id: String? = null
)

class Party {
    @field:Element(name="id") var id :Int = 0
    @field:Element(name="votes") var votes :Int = 0
}

@Root(name = "districtThree")
data class LinkResponse @JvmOverloads constructor(
    @field:ElementList(inline = true, required = false) var parties :List<Party> = ArrayList()
)
