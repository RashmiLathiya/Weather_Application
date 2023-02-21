package com.example.weather

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.view.DetailActivity

class HistoryAdapter(
    private var lngList: ArrayList<String>,
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.history_list,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTiime.text = lngList.get(position)
    }

    override fun getItemCount(): Int {
        return lngList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivInfo: ImageView = itemView.findViewById(R.id.ivInfo)
        val tvTiime : TextView = itemView.findViewById(R.id.tv_time)
        val lngTV: TextView = itemView.findViewById(R.id.tv_temp)


        val context = itemView.context

    }

}

