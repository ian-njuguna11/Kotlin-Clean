package com.paul.repo

import com.paul.entity.PartyDataClass
import java.util.*

interface IPartyRepo: IBaseRepo<Long, PartyDataClass> {
    fun findByName(name: String): Optional<PartyDataClass>
}