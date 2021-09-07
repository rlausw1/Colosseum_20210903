package com.nepplus.colosseum_20210903.datas

import org.json.JSONObject

class ReplyData(
    var id: Int,
    var content: String,
    var likeCount: Int,
    var hateCount: Int,
    var myLike: Boolean,
    var myHate: Boolean,
    var replyCount: Int
) {

    constructor() : this(0, "", 0, 0, 0, 0, 0)

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


            return replyData
        }

    }

}