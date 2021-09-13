package com.bogomolov.weathertapp

interface SomeInterface {
    val engine: String
    fun doSomething(): Int
    fun calculateValue(inputData: Int): Int {
        return inputData + inputData
    }
}