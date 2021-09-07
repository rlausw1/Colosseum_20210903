package com.nepplus.colosseum_20210903.datas

import org.json.JSONObject

class SideData(
    var id: Int,
    topicId: Int,
    var title: String,
    var voteCount: Int
) {


    constructor() : this(0, 0, "미정", 0)
    companion object {

//        json을 넣으면

        fun getSideDataFromJson(json : JSONObject) : SideData {

            val sideData = SideData()

            sideData.id = json.getInt("id")
            sideData.topicId = json.getInt("topic_id")
            sideData.title = json.getString("title")
            sideData.voteCount = json.getInt("vote_count")


            return sideData


        }
    }

}