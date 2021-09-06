package com.nepplus.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nepplus.colosseum_20210903.datas.TopicData
import com.nepplus.colosseum_20210903.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mTopicList = ArrayList<TopicData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {
    }

    override fun setValues() {

        getMainDataFromServer()
    }

//    서버에서, 메인화면에 보여줄 정보 받아오기
    fun getMainDataFromServer() {

        ServerUtil.getRequestMainData(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

//                응답 - jsonObj 분석 (파싱) => 토론 주제들을 서버가 내려줌

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")

//                서버가 내려주는 토론주제들 (JSONObjct 목록 ) =>  TopicData로 변환해서 ArrayList에 추가(반복문추가)

                for ( i in 0 until topicsArr.length()) {
//                    순서에 맞는 {} 를 통째로 받아내기 => 토론 주제 하나하나 담기
                    val topicObj = topicsArr.getJSONObject(i)

//                    topicData를 만들어서 -> 멤ㅂ변수들에 -> topicObj에서 파싱한 데이터를 대입

                    val tempTopicData = TopicData()





                }

            }
        })

    }
}