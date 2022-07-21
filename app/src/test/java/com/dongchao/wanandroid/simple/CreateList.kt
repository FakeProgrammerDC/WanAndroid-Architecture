package com.dongchao.wanandroid.simple

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CreateList {

    fun createList() {
        val arrayList = ArrayList<String>()
        val linkedList = LinkedList<String>()
        val hashMap = HashMap<String, String>();
        //不可变list
        val list = listOf("test", "test2")
        val maxLength = list.maxByOrNull { it.length }
        //可变list
        val mutableList = mutableListOf<Person>(Person("test"), Person("test2"))
        mutableList.add(Person("test3"))

        for (item in mutableList) {
            println(item.name)
        }
    }

    fun createMap() {
        //不可变,且自动推导类型
        val map = mapOf("apple" to 1, "banana" to 2)
        //可能变
        val mutableMap = mutableMapOf("apple" to 1, "banana" to 2)
        mutableMap["pear"] = 3
        //获取数据
        //val number = mutableMap["apple"]
        for ((key, value) in mutableMap) {
            println("key is $key value is $value")
        }

        val maxLength = map.keys.maxByOrNull { it.length }
    }

}