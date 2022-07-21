package com.dongchao.wanandroid.simple

import com.dongchao.wanandroid.ExampleUnitTest

class Student(val sno: String, val grade: Int, name: String) : Person(name) {

    private lateinit var student: Student

    init {
        //判断对象是否初始化
        if (!::student.isInitialized) {
            student = Student("", 0, "")
        }
        //println("sno is $sno")
    }

    companion object {
        @JvmStatic
        fun method() {
        }
    }
}

//    class Student : Person {
//        constructor(name: String) : super(name)
//    }