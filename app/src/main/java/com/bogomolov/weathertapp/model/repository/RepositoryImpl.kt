package com.bogomolov.weathertapp.model.repository

import com.bogomolov.weathertapp.model.WeatherLoader
import com.bogomolov.weathertapp.model.database.Database
import com.bogomolov.weathertapp.model.database.HistoryEntity
import com.bogomolov.weathertapp.model.entities.City
import com.bogomolov.weathertapp.model.entities.Weather
import com.bogomolov.weathertapp.model.rest.WeatherRepo
import com.bogomolov.weathertapp.model.rest.rest_entities.WeatherDTO

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lng: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat, lng).execute().body()

        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()

    override fun getAllHistory(): List<Weather> =
        convertHistoryEntityToWeather(Database.db.historyDao().all())


    override fun saveEntity(weather: Weather) {
        Database.db.historyDao().insert(convertWeatherToEntity(weather))
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> =
        entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }


    private fun convertWeatherToEntity(weather: Weather): HistoryEntity =
        HistoryEntity(0, weather.city.city,
            weather.temperature ?: 0,
            weather.condition ?: ""
        )
}