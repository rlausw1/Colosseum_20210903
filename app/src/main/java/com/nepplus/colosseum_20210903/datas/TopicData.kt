package com.nepplus.colosseum_20210903.datas

import java.io.Serializable

class TopicData(var id : Int,
                var title : String,
                var imageURL : String)  : Serializable {

//    보조생성자 추가.
    constructor() : this(0, "제목 없음", "")



}