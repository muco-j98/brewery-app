package com.example.breweryapp.domain.use_case

import com.example.breweryapp.domain.entities.BreweryModel
import com.example.breweryapp.domain.repository.BreweryRepository
import com.example.breweryapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreweries @Inject constructor(
    private val repository: BreweryRepository
) {

    operator fun invoke(cityName: String): Flow<Resource<List<BreweryModel>>> {
        if (cityName.isBlank()) {
            return flow { }
        }
        return repository.getBreweries(cityName)
    }
}