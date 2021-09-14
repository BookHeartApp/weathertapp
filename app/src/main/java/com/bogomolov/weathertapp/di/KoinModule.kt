package com.bogomolov.weathertapp.di

import com.bogomolov.weathertapp.framework.ui.details.DetailsViewModel
import com.bogomolov.weathertapp.framework.ui.history.HistoryViewModel
import com.bogomolov.weathertapp.framework.ui.list_of_cities.ListOfCitiesViewModel
import com.bogomolov.weathertapp.model.repository.Repository
import com.bogomolov.weathertapp.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single <Repository> {RepositoryImpl()}
    //View models
    viewModel {ListOfCitiesViewModel(get())}
    viewModel { DetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}