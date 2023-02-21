package com.example.weather

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.example.WeatherResponse
import com.example.weather.model.WeatherInfoShowModel
import com.example.weather.model.WeatherInfoShowModelImpl
import com.example.weather.model.data.WeatherData
import com.example.weather.network.RequestCompleteListener
import com.example.weather.utills.kelvinToCelsius
import com.example.weather.utills.unixTimestampToDateTimeString
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_info.*


class HistroyActivity : AppCompatActivity(){

    val weatherInfoLiveData = MutableLiveData<WeatherData>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()
    private lateinit var model: WeatherInfoShowModel
    lateinit var recyclerAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        model = WeatherInfoShowModelImpl(applicationContext)
        val cityName:String = intent.getStringExtra("CITY_History").toString()

      //  getWeatherInfo(cityName,model)
       // setWeatherInfo()

        val tasksSet = PreferenceManager.getDefaultSharedPreferences(this)
            .getStringSet("tasks_set", HashSet())
        val tasksList: ArrayList<String> = ArrayList(tasksSet)
        recyclerAdapter = HistoryAdapter(tasksList)
        recycler_view.adapter = recyclerAdapter
        recyclerAdapter.notifyDataSetChanged()

    }

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

    private fun setWeatherInfo(){
        weatherInfoLiveData.observe(this, Observer { weatherData ->
            setWeatherInfo(weatherData)
        })

        weatherInfoFailureLiveData.observe(this, Observer { errorMessage ->
        //    tv_error_message.visibility = View.VISIBLE
         //   tv_error_message.text = errorMessage
        })
    }

    private fun setWeatherInfo(weatherData: WeatherData) {
      //  tv_error_message.visibility = View.GONE
     //   tv_time.text = weatherData.dateTime
    }


}
