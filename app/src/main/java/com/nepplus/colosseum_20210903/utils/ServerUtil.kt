package com.nepplus.colosseum_20210903.utils

import android.app.DownloadManager
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    단순 기능 수행 -> 서버에 요청을 날리고 -> 응답을 화면에 전달

//    응답을 화면에 전달 : 나에게 발생한 이벤트를 -> 화면단에게 대신 해달라고 한다 (Interface 활용)
    interface JsonResponseHandler {
        fun onResponse( jsonObject: JSONObject )
    }

//    어떤 객체가 하던, 요청/응답 처리만 잘 되면 그만
//    이런 함수를 만든다? => static 함수들로 활용. ServerUtil.기능( ) 코드 작성 가능

    companion object {

//        이 안에 만드는 변수 / 함수는 전부 static 처럼 동작함

//        호스트 주소를 애초에 변수로 저장해두자 (가져다 쓰기 편하게 - ServerUtil안에서만)

        private val HOST_URL = "http://54.180.52.26"

//        로그인 기능 실행 함수
//        아이디/비번 전달 + 서버에 다녀오면 어떤 일을 할지? 인터페이스 객체 전달

        fun postRequestSignIn(id: String, pw: String, handler : JsonResponseHandler) {

//            1. 어디로(url) 갈것인가? HOST_URL + Endpoint
//
            val urlString = "${HOST_URL}/user"

//            2. 어떤 데이터를 들고 갈것인가? 파라미터

            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()


//                    3. 어떤 방식 접근? ㄱㄷ볃
//            모두 모아서 하나의 request 정보로 만들어주자

            val request = Request
                .Builder()
                .url(urlString)
                .post(formData)
                .build()

//            만들어진 Request를 실제로 호출해야함
//            요청을 한다 -> 클라이언트의 역할 ->앱이 클라이언트로 동작

            val client = OkHttpClient()

//만들어진 요청 호출 => 응답이 왔을떄 분석 / UI 반영
//            호출을 하면 -> 응답 받아서 처리 ( 처리할 코드 등록)
            client.newCall(request).enqueue(object  : Callback {
                override fun onFailure(call: Call, e: IOException) {

//                    실패? 서버 연결 자체를 실패 응답 X
//                    비번 틀려서 로그인 실패 : 응답은 돌아왔는데, 그 내용이 실패 (응답O)
//                    인터넷 끊김, 서버 접속 불가 등. 실패 O


                }

                override fun onResponse(call: Call, response: Response) {

                    //                어떤 내용이든, 응답이 돌아온 경우 ( 로그인 성공, 실패 모두 응답)
//                    응답에 포함된 데이터들중 -> 본문(body)을 보자

                    val bodyString = response.body!!.string()

//                    본문을 그냥 String으로 찍어보면 -> 한글이 깨져보임
//                    JSONObject 형태로 변환해서 -> 다시 String으로 바꿔보면 한글이 보임
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답본문", jsonObj.toString())

//                    code값 추출 연습 -> 화면에서 분석해서, 토스트를 띄우는 등의 ㅕUI 처리

//                    val code = jsonObj.getInt("code")
//
//                    Log.d("코드값", code.toString())

//받아낸 jsonObj 를 통째로 -> 화면의 응답 처리 코드에  넘겨주자
                    handler?.onResponse(jsonObj)

                }


            })





    }


}


}