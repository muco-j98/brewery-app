package com.example.breweryapp.domain.entities

import android.app.Activity
import android.content.Context
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.breweryapp.utils.Utils
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Entity(tableName = "brewery_table")
data class BreweryModel(
    val address_2: String?,
    val address_3: String?,
    val brewery_type: String?,
    val city: String?,
    val country: String?,
    val county_province: String?,
    val created_at: String?,
    @PrimaryKey
    val id: String,
    val latitude: String?,
    val longitude: String?,
    val name: String?,
    val phone: String?,
    val postal_code: String?,
    val state: String?,
    val street: String?,
    val updated_at: String?,
    val website_url: String?
) {
    @delegate:Ignore
    private val latitudeFloat: Double by CoordinateDelegate(latitude)
    @delegate:Ignore
    private val longitudeFloat: Double by CoordinateDelegate(longitude)

    fun distance(context: Context): Double {
        val sharedPref = context.getSharedPreferences(Utils.MY_PREFERENCE, Activity.MODE_PRIVATE)
        val lat1 = latitudeFloat
        val lon1 = longitudeFloat

        val lat2 = sharedPref.getFloat(Utils.LATITUDE, 0F).toDouble()
        val lon2 = sharedPref.getFloat(Utils.LONGITUDE, 0F).toDouble()
        val theta = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + (cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return (dist * 100.0).roundToInt() / 100.0
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}