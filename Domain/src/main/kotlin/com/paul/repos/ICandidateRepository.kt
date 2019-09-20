package com.paul.repos

import com.paul.entity.BaseEntity

interface ICandidateRepository<T, I>: IBaseRepository<T, I>{
    fun findAllByPoliticalPartyId(politicalPartyId: Int): ArrayList<T>
    fun findAllByPoliticalPostId(politicalPostId: Int): ArrayList<T>
    fun findByNationalId(nationalId: String): T?
}