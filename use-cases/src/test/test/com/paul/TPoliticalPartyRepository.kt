package com.paul

import com.paul.entity.PoliticalParty
import com.paul.repos.IPoliticalPartyRepository


class TPoliticalPartyRepository : TBaseRepository<PoliticalParty, Int>(), IPoliticalPartyRepository<PoliticalParty, Int>