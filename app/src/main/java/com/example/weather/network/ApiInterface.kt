package com.example.weather.network

import com.example.example.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("weather?")
    fun callWeatherApi(@Query("q") cityName: String): Call<WeatherResponse>

    @GET("")
    fun callHistoryApi(@Query("q") cityName: String): Call<WeatherResponse>
}