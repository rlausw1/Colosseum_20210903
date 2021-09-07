package com.nepplus.colosseum_20210903.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nepplus.colosseum_20210903.R
import com.nepplus.colosseum_20210903.ViewTopicDetailActivity
import com.nepplus.colosseum_20210903.datas.ReplyData
import com.nepplus.colosseum_20210903.datas.TopicData
import com.nepplus.colosseum_20210903.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat

class ReplyAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<ReplyData>
) : ArrayAdapter<ReplyData>(mContext, resId, mList) {


    val mInflater = LayoutInflater.from(mContext)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var row = convertView
        if (row == null) {
            row = mInflater.inflate(R.layout.reply_list_item, null)

        }
        row!!

        val data = mList[position]

        val selectedSideTxt = row.findViewById<TextView>(R.id.selectedSideTxt)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val createdAtTxt = row.findViewById<TextView>(R.id.createdAtTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val replyCountTxt = row.findViewById<TextView>(R.id.replyCountTxt)
        val likeCountTxt = row.findViewById<TextView>(R.id.likeCountTxt)
        val hateCountTxt = row.findViewById<TextView>(R.id.hateCountTxt)


        contentTxt.text = data.content
        replyCountTxt.text = "답글 ${data.replyCount}개"
        likeCountTxt.text = "좋아요 ${data.likeCount}개"
        hateCountTxt.text = "싫어요 ${data.hateCount}개"

        selectedSideTxt.text = "(${data.selectedSide.title})"

        writerNicknameTxt.text = data.writer.nickname


        createdAtTxt.text = data.getFormattedTimeAgo()

        likeCountTxt.tag = true
        hateCountTxt.tag = false

//        해당 댓글에 좋아요/싫어요 찍었다 : 서버에 전송

//        api : POST - topic_reply-like
//        토큰값 / 댓글 id/ true/false (좋아요 싫어요) 파라미터
//        도전과제 : 두개의 텍스트뷰가 눌리면 할일이 거의 동일
//        차이점 - true 보내냐, flase를 보내냐

        val ocl = object : View.OnClickListener {
            override fun onClick(view: View?) {

                val isLike = view!!.tag.toString().toBoolean()

                ServerUtil.postRequestReplylikeOrHate(mContext, data.id, isLike, object
                    : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObject: JSONObject) {

                        //어댑터 안에서 -> ViewTopicDetailActivity의 (mContext변수에 담겨있다!) 기능 실행.

                        (mContext as ViewTopicDetailActivity).getTopicDetailDataFromServer()

                    }
                })
            }
        }

//        tag 속성 이용, 하나의 코드에서 두개 대응
//        추가설명 : 좋아요 / 싫어요 갯수 바로 변경되도록  (어댑터 -> 액티비티의 기능 실행)

        likeCountTxt.setOnClickListener(ocl)
        hateCountTxt.setOnClickListener(ocl)




        return row
    }
}