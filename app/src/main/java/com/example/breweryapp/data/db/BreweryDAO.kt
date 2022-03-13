package com.example.breweryapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.breweryapp.domain.entities.BreweryModel

@Dao
interface BreweryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreweries(breweries: List<BreweryModel>)

    @Query("DELETE FROM brewery_table WHERE id IN (:breweries)")
    suspend fun deleteBreweries(breweries: List<String>)

    @Query("SELECT * FROM brewery_table WHERE city LIKE '%' || :cityName || '%'")
    suspend fun getBreweries(cityName: String): List<BreweryModel>
}