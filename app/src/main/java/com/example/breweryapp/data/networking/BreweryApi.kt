package com.example.breweryapp.data.networking

import com.example.breweryapp.domain.entities.BreweryModel
import com.example.breweryapp.utils.Utils
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryApi {

    @GET(Utils.GET_BREWERIES_BY_CITY)
    suspend fun getBreweriesByCity(@Query("by_city") byCity: String): List<BreweryModel>
}