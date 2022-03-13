package com.example.breweryapp.data.repository

import com.example.breweryapp.data.db.BreweryDAO
import com.example.breweryapp.data.networking.BreweryApi
import com.example.breweryapp.domain.entities.BreweryModel
import com.example.breweryapp.domain.repository.BreweryRepository
import com.example.breweryapp.utils.Resource
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BreweryRepositoryImpl @Inject constructor(
    private val api: BreweryApi,
    private val dao: BreweryDAO
) : BreweryRepository {
    override fun getBreweries(cityName: String): Flow<Resource<List<BreweryModel>>> = flow {
        emit(Resource.Loading())

        val breweries = dao.getBreweries(cityName)
        emit(Resource.Loading(data = breweries))

        try {
            val remoteBreweries = api.getBreweriesByCity(cityName)
            dao.deleteBreweries(remoteBreweries.map { it.id })
            dao.insertBreweries(remoteBreweries)
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Something went wrong", data = breweries))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server", data = breweries))
        }

        val newBreweries = dao.getBreweries(cityName)
        emit(Resource.Success(newBreweries))
    }
}