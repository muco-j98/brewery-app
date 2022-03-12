package com.example.breweryapp.presentation

import com.example.breweryapp.domain.entities.BreweryModel

data class BreweryInfoState(
    val breweries: List<BreweryModel> = emptyList(),
    val isLoading: Boolean = false
)