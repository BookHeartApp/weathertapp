package com.bogomolov.weathertapp.framework.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bogomolov.weathertapp.AppState
import com.bogomolov.weathertapp.model.entities.Weather
import com.bogomolov.weathertapp.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(lat: Double, lng: Double) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lng)
            repository.saveEntity(data)
            liveDataToObserve.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}