package com.paul.repos

import com.paul.entity.BaseEntity

interface IBaseRepository <T, I> {
    fun create(t: BaseEntity<I>): BaseEntity<I>
    fun merge(t: BaseEntity<I>): BaseEntity<I>
    fun findById(id: I): BaseEntity<I>?
    fun findByName(name: String): BaseEntity<Int>?
    fun findAll(): ArrayList<BaseEntity<I>>
    fun generateId(): I?
}