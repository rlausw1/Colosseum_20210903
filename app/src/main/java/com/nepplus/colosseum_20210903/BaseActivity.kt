package com.nepplus.colosseum_20210903

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext : Context
    override fun onCreate(savedInstanceState: Bundle?, ) {
        super.onCreate(savedInstanceState)

        mContext = this

//        모든 화면의 onCreate에서 커스텀액션바 적용
//        액션바가 존재하는 화면에서만 실행
//        액션바가 존재할때만 -> 별개의 함수를 실행

        supportActionBar?.let {
//            액션바가 null이 아닐때만ㄴ 실행시켜줄 코드
            setCustomActionBar()
        }


    }

    abstract fun setupEvents()
    abstract fun setValues()

//    액션바를 커스텀 액션바로 바꿔주는 함수

    fun setCustomActionBar() {

//        기본액션바를 불러오자
        val defaultActionBar = supportActionBar!!

//        커스텀모드로 변경 -> 우리가 만든  xml로 적용
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

//        양 옆에 여백 제거 -> 모든 영역이 커스텀뷰
        val myToolbar = defaultActionBar.customView.parent as Toolbar
        myToolbar.setContentInsetsAbsolute(0,0)


    }

}