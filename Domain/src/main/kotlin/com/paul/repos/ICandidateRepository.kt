package com.paul.repos

import com.paul.entity.BaseEntity

interface ICandidateRepository<T, I>: IBaseRepository<T, I>{
    fun findAllByPoliticalPartyId(politicalPartyId: Int): ArrayList<BaseEntity<T>>
    fun findAllByPoliticalPostId(politicalPostId: Int): ArrayList<BaseEntity<T>>
    fun findByNationalId(nationalId: String): BaseEntity<T>?
}