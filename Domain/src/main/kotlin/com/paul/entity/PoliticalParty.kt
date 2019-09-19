package com.paul.entity

data class PoliticalParty(
    override var id: Int? = 0,
    override var avscFilename: String = "",
    override var topic: String = "political_party",
    override var name: String = ""
): BaseEntity<Int>(){

    override fun toMap(): HashMap<Any, Any> {
        return super.toMap().apply {
            this["id"] = id!!
        }
    }

}
