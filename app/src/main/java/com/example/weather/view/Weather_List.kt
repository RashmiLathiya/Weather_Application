package com.example.weather.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.RecyclerAdapter

class Weather_List : AppCompatActivity() {

        lateinit var lngRV: RecyclerView
        lateinit var addEdt: EditText
        lateinit var addBtn: Button
        lateinit var lngList: ArrayList<String>
        lateinit var recyclerAdapter: RecyclerAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_weather)

            lngRV = findViewById(R.id.recycler_view)
            addEdt = findViewById(R.id.id_Edt_Add)
            addBtn = findViewById(R.id.id_btn_add)
            lngList = ArrayList()

            lngList.plusAssign("London")
            lngList.plusAssign("Vienna")
            lngList.plusAssign("Paris")

            recyclerAdapter = RecyclerAdapter(lngList = lngList)
            lngRV.adapter = recyclerAdapter

            addBtn.setOnClickListener {
                addItem(addEdt.text.toString())
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