package com.nepplus.colosseum_20210903

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.nepplus.colosseum_20210903.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())
        myHandler.postDelayed({

//            1. 자동로그인 여부 판단 -> 상황에 따라 다른 화면으로 넘어가게
//        다른 화면 : Intent의 목적지만 달라진다

            val myIntent : Intent

//        자동로그인 여부 : 사용자가 자동로그인 하겠다 + 저장된 토큰이 유효(들어있다)하다
            if (ContextUtil.getAutoLogIn(mContext) && ContextUtil.getToken(mContext)!="" ) {

//            둘다 만족 : 자동로그인 ok -> 메인화면으로 이동
                myIntent = Intent(mContext, MainActivity::class.java)

            }
            else {
//            하나라도 만족 안됨 : 자동 로그인 실패 -> 로그인 화면으로 이동
                myIntent = Intent(mContext, SignInActivity::class.java)
            }

            startActivity(myIntent)
            finish()


        }, 2500)

//


    }
}