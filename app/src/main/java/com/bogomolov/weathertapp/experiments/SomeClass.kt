package com.bogomolov.weathertapp.experiments

import android.view.View

class SomeClass<T, V> {
    private var someField: T? = null
    private var someField2: V? = null

    fun setSomeField(someData: T?) {
        someField = someData
    }

    fun getSomeField(): T? {
        return someField
    }

    //viewContainer = наследник от View
    fun someFun(viewContainer: Container<out View>) {

    }

    fun <T> compare(list: List<T>, comparator: Comparator<in T>) {

    }

    fun doSomething() {
        compare(listOf("a", "b"), ComparatorImpl())
    }

    interface Container<T> {
        fun getData(): T
    }
}

class ComparatorImpl : Comparator<CharSequence> {
    override fun compare(o1: CharSequence?, o2: CharSequence?): Int {
        return 1
    }
}