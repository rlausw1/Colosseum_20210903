package com.nepplus.colosseum_20210903.utils

import android.app.DownloadManager

class ServerUtil {

//    단순 기능 수행 -> 서버에 요청을 날리고 -> 응답을 화면에 전달
//    어떤 객체가 하던, 요청/응답 처리만 잘 되면 그만
//    이런 함수를 만든다? => static 함수들로 활용. ServerUtil.기능( ) 코드 작성 가능

    companion object {

//        이 안에 만드는 변수 / 함수는 전부 static 처럼 동작함

//        호스트 주소를 애초에 변수로 저장해두자 (가져다 쓰기 편하게 - ServerUtil안에서만)

        private val HOST_URL = "http://54.180.52.26"

//        로그인 기능 실행 함수

        fun postRequestSignIn( id : String, pw : String ) {

//            1. 어디로 갈것인가?
//
            val urlString = "${HOST_URL}/user"

//            2. 어떤 데이터를 들고 갈것인가? 파라미터

            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()




//                    3. 어떤 방식 접근? ㄱㄷ볃
//            모두 모아서 하나의 request 정보로 만들어주자

            val request = Request.Builder()
                .url(urlString)
                .post(formData)
                .build()

//            만들어진 Request를 실제로 호출해야함
//            요청을 한다 -> 클라이언트의 역할 ->앱이 클라이언트로 동작

            val client = OkHttpClient()


            client.newCall(request)




        }










    }


}