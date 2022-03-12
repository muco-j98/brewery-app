package com.example.breweryapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.breweryapp.domain.entities.BreweryModel

@Database(
    entities = [BreweryModel::class],
    version = 3
)
abstract class BreweryDatabase: RoomDatabase() {

    abstract val dao: BreweryDAO
}