package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.WeatherResponse
import com.example.weather.model.WeatherInfoShowModel
import com.example.weather.model.data.WeatherData
import com.example.weather.network.RequestCompleteListener
import com.example.weather.utills.kelvinToCelsius
import com.example.weather.utills.unixTimestampToDateTimeString

class WeatherInfoViewModel : ViewModel() {

    val weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()

    fun getWeatherInfo(cityName: String, model: WeatherInfoShowModel) {
        model.getWeatherInfo(cityName, object :
            RequestCompleteListener<WeatherResponse> {
            override fun onRequestSuccess(data: WeatherResponse) {

                val weatherData = WeatherData(
                    cityAndCountry = "${data.name}, ${data.sys?.country}",
                    weatherConditionIconUrl = "http://openweathermap.org/img/w/${data.weather[0].icon}.png",
                    weatherConditionIconDescription = data.weather[0].description.toString(),
                    temperature = data.main?.temp?.kelvinToCelsius().toString(),
                    humidity = "${data.main?.humidity}%",
                    wind = data.wind?.speed.toString(),
                    dateTime = data.dt?.unixTimestampToDateTimeString().toString()
                )
                weatherInfoLiveData.postValue(weatherData)
            }

            override fun onRequestFailed(errorMessage: String) {
                weatherInfoFailureLiveData.postValue(errorMessage)
            }
        })
    }

}