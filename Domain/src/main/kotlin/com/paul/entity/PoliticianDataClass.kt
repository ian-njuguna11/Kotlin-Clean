package com.paul.entity

data class PoliticianDataClass(
    val id: Int = 0,
    val user: UserDataClass = UserDataClass(),
    val partyId: Int = 0,  // references to the ID in the PartyDataClass
    val postId: Int = 0    // references to the ID in the PostDataClass
)
