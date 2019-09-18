package com.paul.entity

data class Voter(
    override var id: Int? = 0,
    override var avscFilename: String = "",
    override var topic: String = "voter",
    override var name: String = "",
    var nationalId: String = ""
): BaseEntity<Int>(){

    override fun toMap(): HashMap<Any, Any> {
        return super.toMap().apply {
            this["nationalId"] = nationalId
        }
    }

}