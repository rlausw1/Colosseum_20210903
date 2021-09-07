package com.nepplus.colosseum_20210903.datas

import org.json.JSONObject

class ReplyData(
    var id: Int,
    var content: String,
    var likeCount: Int,
    var hateCount: Int,
    var myLike: Boolean,
    var myHate: Boolean,
    var replyCount: Int) {

//    replyData 하위개념
    lateinit var selectedSide : SideData

    lateinit var writer : UserData

    constructor() : this(0, "", 0, 0, false, false, 0)

    companion object {

        //        JSON을 넣으면 -> ReplyData로 변환해주는 기능
        fun getReplyDataFromJson(json: JSONObject): ReplyData {
            val replyData = ReplyData()

            replyData.id = json.getInt("id")
            replyData.content = json.getString("content")
            replyData.likeCount = json.getInt("like_Count")
            replyData.hateCount = json.getInt("dislike_Count")
            replyData.myLike = json.getBoolean("my_like")
            replyData.myHate = json.getBoolean("my_dislike")
            replyData.replyCount = json.getInt("reply_count")

//            선택진영 파싱 -> SideDat에 만둘어둔 파싱 기능 활용

            val selectedSideObj = json.getJSONObject("selected_side")
            replyData.selectedSide = SideData.getSideDataFromJson()

//작성자 정보 파싱 -> UserData의 기능 활용
            val userObj = json.getJSONObject("user")
            replyData.writer = UserData.getUserDataFromJson()



            return replyData
        }

    }

}