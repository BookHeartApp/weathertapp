package com.bogomolov.weathertapp.model.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bogomolov.weathertapp.model.entities.City

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val city: String,
    val temperature: Int,
    val condition: String
)