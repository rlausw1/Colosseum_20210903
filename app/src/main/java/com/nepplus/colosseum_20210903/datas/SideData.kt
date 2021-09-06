package com.nepplus.colosseum_20210903.datas

class SideData(
    var id: Int,
    topicId: Int,
    var title: String,
    var voteCount: Int
) {


    constructor() : this(0, 0, "미정", 0)
}