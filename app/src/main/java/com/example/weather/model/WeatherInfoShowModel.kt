package com.example.weather.model

import com.example.example.WeatherResponse
import com.example.weather.network.RequestCompleteListener


interface WeatherInfoShowModel {
    fun getWeatherInfo(cityName: String, callback: RequestCompleteListener<WeatherResponse>)
    fun getHistroyInfo(cityName: String, callback: RequestCompleteListener<WeatherResponse>)
}