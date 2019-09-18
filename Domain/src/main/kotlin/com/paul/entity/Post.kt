package com.paul.entity

data class Post(
    override var id: Int? = 0,
    override var avscFilename: String = "",
    override var topic: String = "political_party",
    override var name: String = ""
): BaseEntity<Int>()