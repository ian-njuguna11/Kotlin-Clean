package com.paul.entity

data class Vote(
    override var id: Int? = 0,
    override var topic: String = "candidate",
    override var avscFilename: String = "",
    override var name: String = "",
    var nationalId: String = "",
    var voterName: String = "",
    var postId: Int = 0,
    var postName: String = "",
    var candidateId: Int = 0,
    var candidateName: String = ""
): BaseEntity<Int>(){

    override fun toMap(): HashMap<Any, Any> {
        return super.toMap().apply {
            this["id"] = id!!
            this["national_id"] = nationalId
            this["voter_name"] = voterName
            this["post_id"] = postId
            this["post_name"] = postName
            this["candidate_id"] = candidateId
            this["candidate_name"] = candidateName
        }
    }

}