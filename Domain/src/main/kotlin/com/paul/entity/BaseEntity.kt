package com.paul.entity

open class BaseEntity<T>(
    open var id: T? = null,
    open var avscFilename: String = "",
    open var topic: String = "",
    open var name: String = ""
){

    open fun toMap():HashMap<Any, Any>{
        val baseMap = HashMap<Any, Any>()
        baseMap["avscFilename"] = this.avscFilename
        baseMap["topic"] = this.topic
        baseMap["name"] = this.name
        return baseMap
    }

}
