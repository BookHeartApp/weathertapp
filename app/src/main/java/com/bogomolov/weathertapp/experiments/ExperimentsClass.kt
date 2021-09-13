package com.bogomolov.weathertapp.experiments

import android.app.Activity
import android.os.Bundle
import com.bogomolov.weathertapp.SomeInterface
import com.bogomolov.weathertapp.format
import com.bogomolov.weathertapp.model.entities.City
import com.bogomolov.weathertapp.model.entities.Weather
import java.util.*
import kotlin.collections.ArrayList

class ExperimentsClass : Activity(), SomeInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.getSerializableExtra("DATA") as? City

        data?.let {
            print(it)
        } ?: run {
            val runValue = 10
        }

        run {
            val a = 30

        }

        val intValue = 1
        val doubleValue = intValue.toDouble()

        val result = if(data is City) {
            a()
        } else {
            b()
        }
        if(result is Unit) {

        } else {

        }
        var arrayList1 = arrayListOf("i", "k")
        val arrayList = ArrayList<String>()
        val array2 = Array<Int>(5) {0}
        val a = arrayList1[0]
        val b = array2[0]

        arrayList1[0] = "j"
        val someList = mutableListOf(
            Weather(temperature = 5, feelsLike = 3),
            Weather(temperature = 7, feelsLike = 4)
        )
        someList[0] = Weather(temperature = 5, feelsLike = 3)
        val filteredList = someList.filter {
            it.temperature == 5
        }.map {
            it.feelsLike
        }
        arrayList1.indexOfFirst { it  == "k"} //1
        arrayList1.forEach {
            print(it)
        }

        val weather = Weather().apply {

        }.also {
            val alsoVal = 50
        }.run {

        }



        val someclass = SomeClass<Int, Double>()

        someValFun
        val res = sum(10, 20)

        val date = Date().format()
    }

    fun a() {

    }

    fun b(): Int {
        return 1
    }

    override val engine: String
        get() = "TODO(Not yet implemented)"

    override fun doSomething() = 5

    val someValFun = { print("Hi") }
    val sum = { a: Int, b: Int -> a + b }

    fun printMy(block: () -> Unit) {
        println(block())
        someValFun
    }
}