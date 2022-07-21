package com.dongchao.wanandroid.simple

object Operator {
    fun getTextLength(text: String?) = text?.length ?: 0

    var student: Student? = null

    fun doStudy() {
        student?.let {
            it.name
            it.sno
        }
    }

    fun print(num: Int = 0) = num

    fun getResult() = with(StringBuffer()) {
        append("start")
        append("content")
        append("end")
        toString()//最后一行,函数返回值
    }

//    fun getResult() = StringBuffer().run {
//        append("start")
//        append("content")
//        append("end")
//        toString()//最后一行,函数返回值
//    }

//    fun getResult() = StringBuffer().apply {
//        append("start")
//        append("content")
//        append("end")
//    }.toString()


}