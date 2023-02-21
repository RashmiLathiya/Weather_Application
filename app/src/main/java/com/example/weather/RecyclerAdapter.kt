package com.example.weather

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.view.DetailActivity

class RecyclerAdapter(
    private var lngList: ArrayList<String>,
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.weather_list,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lngTV.text = lngList.get(position)
    }

    override fun getItemCount(): Int {
        return lngList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lngTV: TextView = itemView.findViewById(R.id.kodePertanyaan)
        val ivInfo: ImageView = itemView.findViewById(R.id.ivInfo)
        val context = itemView.context
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("CITY", lngTV.text.toString())
                }
                context.startActivity(intent)
            }

            ivInfo.setOnClickListener {
                val intent = Intent(context, HistroyActivity::class.java).apply {
                    putExtra("CITY_History", lngTV.text.toString())
                }
                context.startActivity(intent)
            }
        }

    }

}

