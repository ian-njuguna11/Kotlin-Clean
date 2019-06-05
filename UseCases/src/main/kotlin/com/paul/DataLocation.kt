package com.paul

import com.paul.entity.*

class DataLocation {

    companion object {

        var userList = ArrayList<UserDataClass>()
        var politicianList = listOf<PoliticianDataClass>()
        var postList = listOf<PostDataClass>()
        var voteList = listOf<VoteDataClass>()
        var voterList = listOf<VoterDataClass>()

    }

}