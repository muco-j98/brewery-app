package com.example.breweryapp.domain.repository

import com.example.breweryapp.domain.entities.BreweryModel
import com.example.breweryapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface BreweryRepository {

    fun getBreweries(cityName: String): Flow<Resource<List<BreweryModel>>>
}