package com.paul.repos

import com.paul.entity.BaseEntity

interface IVoteRepository<T, I>: IBaseRepository<T, I> {
    fun findAllByPostId(postId: Int): ArrayList<BaseEntity<I>>
    fun findAllByCandidateId(candidateId: Int): ArrayList<BaseEntity<T>>

}