package com.paul.BaseCases

class AutoGenerateId<T> {

    fun generateId(itemList: ArrayList<T>): Int{
        val length = itemList.size
        return length + 1
    }


}