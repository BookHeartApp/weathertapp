package com.bogomolov.weathertapp.framework.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogomolov.weathertapp.AppState
import com.bogomolov.weathertapp.model.repository.Repository
import java.lang.Thread.sleep

class MainViewModel (private val repository: Repository): ViewModel(){
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

        fun getLiveData() = liveDataToObserve

        fun getWeather() = getDataFromLocalSource()

        private fun getDataFromLocalSource() {
            liveDataToObserve.value = AppState.Loading
            Thread {
                sleep(1000)
                liveDataToObserve.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
            }.start()
        }
    }