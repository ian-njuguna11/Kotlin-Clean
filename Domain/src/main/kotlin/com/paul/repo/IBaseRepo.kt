package com.paul.repo

import java.util.*

// T - type of ID
// I - data class of repo inheriting from BaseRepo
interface IBaseRepo<T, I> {
    fun findById(id: T): Optional<I>
    fun create(item: I): Optional<I>
    fun merge(item: I): Optional<I>
    fun generateId(): T
}