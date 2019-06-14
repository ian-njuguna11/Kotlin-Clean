package com.paul.entity

data class PoliticianDataClass(
    val id: Long = 0L,
    val user: UserDataClass = UserDataClass(),
    val partyId: Int = 0,  // references to the ID in the PartyDataClass
    val postId: Int = 0    // references to the ID in the PostDataClass
)
