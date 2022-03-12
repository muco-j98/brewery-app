package com.example.breweryapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "brewery_table")
data class BreweryModel(
    val address_2: String?,
    val address_3: String?,
    val brewery_type: String,
    val city: String,
    val country: String,
    val county_province: String?,
    val created_at: String,
    @PrimaryKey
    val id: Int,
    val latitude: String,
    val longitude: String,
    val name: String,
    val phone: String,
    val postal_code: String,
    val state: String,
    val street: String,
    val updated_at: String,
    val website_url: String
)