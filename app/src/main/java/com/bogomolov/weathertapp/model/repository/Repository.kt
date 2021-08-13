package com.bogomolov.weathertapp.model.repository

import com.bogomolov.weathertapp.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}