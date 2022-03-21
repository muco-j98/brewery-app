package com.example.breweryapp.domain.entities

import kotlin.reflect.KProperty

class CoordinateDelegate(private val stringValue: String?) {

    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>,
    ): Double {
        stringValue?.let {
            return it.toDouble()
        }
        return 0.0
    }
}