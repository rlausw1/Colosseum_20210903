package com.nepplus.colosseum_20210903

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.nepplus.colosseum_20210903.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        이메일 입력칸의 내용 변경 감지
        emailEdt.addTextChangedListener {
//            if 변수를 활용 : 입력된 내용 파악
            Log.d("이메알 입력 변경", it.toString())

//            실제 코딩 : 변경되기만 하면 => 검사 결과 문구를 "중복 검사를 해주세요"로 변경
            checkEmailResultTxt.text = "중복검사를 해주세요"
        }

        checkEmailBtn.setOnClickListener() {
//            1. 입력한 이메일을 받아서
            val inputEmail = emailEdt.text.toString()

//            2. 서버에 이메일 중복확인 요청 -> 응답에 따라, 결과 텍스트뷰의 문구 수정

            ServerUtil.getRequestDuplCheck("EMAIL", inputEmail, object :ServerUtil.JsonResponseHandler {

                override fun onResponse(jsonObject: JSONObject) {
                    val code = jsonObject.getInt("code")

                    runOnUiThread {

                        if (code ==200) {
                            checkEmailResultTxt.text = "사용해도 좋은 이메일 입니다"

                        }
                        else {
//                        문구변경 - 이메일 중복확인 결과 문구로 반영
                            checkEmailResultTxt.text = "중복된 이메일 입니다. 다른 걸로 입력해주세요"
                        }

                    }


                }

            })
        }



        signUpBtn.setOnClickListener {
//            1. 입력 한 값 받아서

            val inputEmail = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()
            val inputNickname = nicknameEdt.text.toString()

//            2. 서버에 가입 요청 -> 응답에 따라 어떤 처리?

            ServerUtil.putRequestSignUp(inputEmail, inputPw, inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

//                    가입 성공(200) / 실패(200외의 값)
//                    실패 : 이메일 양식 x, 중복된 이메일, 중복된 닉네임

//                    성공일때 -> { } 내부 비워두자
//                    실패시만 동작 -> message 에 담긴 가입 실패 사유를 갖고 -> 토스트로 출력

                    val code = jsonObj.getInt("code")

                    if(code == 200) {
//                        가입에 성공했습니다. 토스트
//                        이전 화면으로 복귀

                        runOnUiThread{
                            Toast.makeText(mContext, "가입에 성공했습니다", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                    }
                    else {
                        val message = jsonObj.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }

                }


            })



        }
    }

    override fun setValues() {
    }
}