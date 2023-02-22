package com.example.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_weather.*

class Weather_List : AppCompatActivity() {

        lateinit var recyclerView: RecyclerView
        lateinit var lngList: ArrayList<String>
        lateinit var recyclerAdapter: RecyclerAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_weather)

            recyclerView = findViewById(R.id.recycler_view)
            lngList = ArrayList()

            lngList.plusAssign("London")
            lngList.plusAssign("Vienna")
            lngList.plusAssign("Paris")

            recyclerAdapter = RecyclerAdapter(lngList = lngList)
            recyclerView.adapter = recyclerAdapter

            button_Add.setOnClickListener {
                addItem(editText_AddCity.text.toString())
            }
            recyclerAdapter.notifyDataSetChanged()

        }

        private fun addItem(item: String) {
            if (item.isNotEmpty()) {
                lngList.plusAssign(item)
                recyclerAdapter.notifyDataSetChanged()
            }
        }

        private fun removeItem(item: String){
            if (item.isNullOrEmpty()) {
                lngList.remove(item)
                recyclerAdapter.notifyDataSetChanged()
            }
        }

}