package com.nepplus.colosseum_20210903.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

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

//    이 댓글을 적은 사람

    lateinit var writer : UserData

//    이 댓글이 적힌 시점(날짜+시간) -> calendar 클래스 활용
//    simpleDataFromJson

    val createdAt = Calendar.getInstance() //일단 현재시간 저장 -> 파싱

    constructor() : this(0, "", 0, 0, false, false, 0)

    companion object {

//        서버가 주는 날짜 양식을 분석하기 위한 SimpleDataFormat
        val serverFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

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
            replyData.selectedSide = SideData.getSideDataFromJson(selectedSideObj)

//            작성자 정보 파싱 -> UserData의 기능 활용
            val userObj = json.getJSONObject("user")
            replyData.writer = UserData.getUserDataFromJson(userObj)

//
            val createdAtString = json.getString("created_at")




            return replyData
        }

    }

}