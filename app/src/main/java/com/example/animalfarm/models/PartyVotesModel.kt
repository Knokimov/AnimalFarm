package com.example.animalfarm.models

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class PartyID(
    @SerializedName("id") var id: String? = null
)

@Root(name = "districtThree")
data class LinkResponse @JvmOverloads constructor(
    @field:ElementList(inline = true, required = false) var partyVotes: List<PartyVotes> = ArrayList()
)

data class PartyVotes(
    @field:Element(name="id") var id: Int = 0,
    @field:Element(name="votes") var votes: Int = 0
)

