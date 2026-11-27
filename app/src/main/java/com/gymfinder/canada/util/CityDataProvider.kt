package com.gymfinder.canada.util

import com.gymfinder.canada.domain.model.City

object CityDataProvider {

    val cities = listOf(
        City("city_toronto", "Toronto", "Ontario", 43.6532, -79.3832),
        City("city_ottawa", "Ottawa", "Ontario", 45.4215, -75.6972),
        City("city_mississauga", "Mississauga", "Ontario", 43.5890, -79.6441),
        City("city_brampton", "Brampton", "Ontario", 43.7315, -79.7624),
        City("city_hamilton", "Hamilton", "Ontario", 43.2557, -79.8711),
        City("city_kitchener", "Kitchener", "Ontario", 43.4516, -80.4925),
        City("city_waterloo", "Waterloo", "Ontario", 43.4643, -80.5204),
        City("city_london", "London", "Ontario", 42.9849, -81.2453),
        City("city_kingston", "Kingston", "Ontario", 44.2312, -76.4860),
        City("city_belleville", "Belleville", "Ontario", 44.1628, -77.3832),
        City("city_vancouver", "Vancouver", "British Columbia", 49.2827, -123.1207),
        City("city_calgary", "Calgary", "Alberta", 51.0447, -114.0719)
    )

    val defaultCity: City = cities[0] // Toronto, Ontario

    fun getCityById(id: String): City {
        return cities.find { it.id == id } ?: defaultCity
    }
}
