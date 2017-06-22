package com.vaporware.reverendcode.alphalist

import java.util.*

/**
 * Created by ReverendCode on 6/19/17.
 * The Trip object is the top level container class that holds all the information for a particular trip
 */

class Trip(var tripName: String,
           var headerImage: Int? = null,
           var tripDate: Date? = null,
           var tripEndDate: Date? = null,
           var packingList: Equipment? = null,
           var destination: Destination? = null,
           var activityTags: List<String>? = null,
           var attendees: List<String>? = null,
           var forecast: Forecast? = null) {


    /*
    * This probably needs to return whatever kind of "weather" object already exists in android
    * and it almost certainly needs to be done asynchronously, since it is going out to the internet for
    * information
    * */
    fun updateForecast(): Unit {
        TODO("query weatherForecast for weather at destination numDays out")
//        val forcaster = Forecaster(destination)
//        try {
//            forecast = forcaster.getForecast(tripDate)
//        } finally {
//            //broadcast Update??
//        }

    }

    fun containsTag(tag: String): Boolean {
        return activityTags?.contains(tag) ?: false
    }

}

data class Destination (
        val latitude: Float,
        val longitude: Float
)

data class Forecast (
        val tempCelcius: Float,
        val conditions: String
)