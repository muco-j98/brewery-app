package com.example.breweryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.breweryapp.domain.use_case.GetBreweriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class BreweryViewModel @Inject constructor(
    private val getBreweriesUseCase: GetBreweriesUseCase
): ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val breweriesFlow = searchQuery.flatMapLatest {
        delay(500L)
        getBreweriesUseCase(it)
    }

    val breweries = breweriesFlow.asLiveData()
}