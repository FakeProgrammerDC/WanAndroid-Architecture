package com.dongchao.wanandroid

import com.dongchao.wanandroid.simple.Operator
import com.dongchao.wanandroid.simple.Person
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test() {
        val list = listOf(Person("test"), Person("test2"), Person("chao test3"))
        val maxLengthData = list.maxByOrNull { it.name.length }
        println("max is ${maxLengthData?.name}")

        Thread { println("Thread is running") }.start()
    }

    @Test
    fun test2() {
        //Operator.doStudy(null)
    }

}