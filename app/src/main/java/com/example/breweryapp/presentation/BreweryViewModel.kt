package com.example.breweryapp.presentation

import androidx.lifecycle.asLiveData
import com.example.breweryapp.domain.use_case.GetBreweries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class BreweryViewModel @Inject constructor(
    private val getBreweries: GetBreweries
) {

    val searchQuery = MutableStateFlow("")

    private val breweriesFlow = searchQuery.flatMapLatest {
        getBreweries(it)
    }

    val breweries = breweriesFlow.asLiveData()

}