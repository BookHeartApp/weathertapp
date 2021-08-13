package com.bogomolov.weathertapp.model.repository

import com.bogomolov.weathertapp.model.entities.Weather

class RepositoryImpl : Repository {

    override fun getWeatherFromServer(): Weather = Weather()

    override fun getWeatherFromLocalStorage(): Weather = Weather()
}
