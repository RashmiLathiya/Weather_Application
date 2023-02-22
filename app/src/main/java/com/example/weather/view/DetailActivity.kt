package com.example.weather.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.model.WeatherInfoShowModel
import com.example.weather.model.WeatherInfoShowModelImpl
import com.example.weather.model.data.WeatherData
import com.example.weather.viewmodel.WeatherInfoViewModel
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    private lateinit var model: WeatherInfoShowModel
    private lateinit var viewModel: WeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        model = WeatherInfoShowModelImpl(applicationContext)
        viewModel = ViewModelProviders.of(this).get(WeatherInfoViewModel::class.java)

        val cityName:String = intent.getStringExtra("CITY").toString()
        getWeatherInfo(cityName)
        setWeatherInfo()

    }
    private fun getWeatherInfo(cityName : String) {
        viewModel.getWeatherInfo(cityName, model)
    }

    private fun setWeatherInfo(){
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherData ->
            setWeatherInfo(weatherData)
            sharedPreferencesTime(weatherData)
        })

        viewModel.weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
            text_error_message.visibility = View.VISIBLE
            text_error_message.text = errorMessage
        })
    }

    private fun setWeatherInfo(weatherData: WeatherData) {
        text_error_message.visibility = View.GONE
        text_temp.text = weatherData.temperature
        text_city.text = weatherData.cityAndCountry
        Glide.with(this).load(weatherData.weatherConditionIconUrl).into(imageView)
        text_description.text = weatherData.weatherConditionIconDescription
        text_humidity.text = weatherData.humidity
        text_wind.text = weatherData.wind
        text_time.text = weatherData.dateTime
    }

    private fun sharedPreferencesTime(weatherData: WeatherData) {
        val listtasks = ArrayList<String>()
        listtasks.add(weatherData.dateTime)
        val tasksSet: Set<String> = HashSet<String>(listtasks)
        PreferenceManager.getDefaultSharedPreferences(applicationContext)
            .edit()
            .putStringSet("tasks_set", tasksSet)
            .commit()
    }


}
