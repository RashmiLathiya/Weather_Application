package com.example.weather.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

    lateinit var cityTextView: TextView
    lateinit var imageView: ImageView
    lateinit var descTextView: TextView
    lateinit var tempTextView: TextView
    lateinit var humTextView: TextView
    lateinit var windTextView: TextView
    private lateinit var model: WeatherInfoShowModel
    private lateinit var viewModel: WeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        model = WeatherInfoShowModelImpl(applicationContext)
        viewModel = ViewModelProviders.of(this).get(WeatherInfoViewModel::class.java)
        tempTextView = findViewById(R.id.tempTextview)
        cityTextView = findViewById(R.id.cityTextview)
        descTextView = findViewById(R.id.descTextview)
        humTextView = findViewById(R.id.humidityTextview)
        imageView = findViewById(R.id.imageView)
        windTextView = findViewById(R.id.windTextview)

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
            tv_error_message.visibility = View.VISIBLE
            tv_error_message.text = errorMessage
        })
    }

    private fun setWeatherInfo(weatherData: WeatherData) {
        tv_error_message.visibility = View.GONE
        tempTextView.text = weatherData.temperature
        cityTextView.text = weatherData.cityAndCountry
        Glide.with(this).load(weatherData.weatherConditionIconUrl).into(imageView)
        descTextView.text = weatherData.weatherConditionIconDescription
        humTextView.text = weatherData.humidity
        windTextView.text = weatherData.wind
        tv_time.text = weatherData.dateTime
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
