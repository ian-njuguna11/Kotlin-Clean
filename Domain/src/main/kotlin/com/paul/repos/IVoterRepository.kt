package com.paul.repos

interface IVoterRepository<T, I>: IBaseRepository<T, I> {
    fun findByNationalId(nationalId: String): T?
}