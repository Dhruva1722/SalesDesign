package com.example.salesdesign.Adapter

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Fragment.LocationInformation
import com.example.salesdesign.R

class LocationAdapter(private val locationInfoList: List<LocationInformation>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(locationItemView: View) : RecyclerView.ViewHolder(locationItemView) {
        val startPointNameTextView: TextView = locationItemView.findViewById(R.id.startPointNameTextView)
        val endPointNameTextView: TextView = locationItemView.findViewById(R.id.endPointNameTextView)
        val distanceTextView: TextView = locationItemView.findViewById(R.id.distanceTextView)
        val timestampTextView: TextView = locationItemView.findViewById(R.id.timestampTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(locationHolder: ViewHolder, position: Int) {
        val locationInfo = locationInfoList[position]
        locationHolder.startPointNameTextView.text = "Start Point: ${locationInfo.startPoint.startPointName}"
        locationHolder.endPointNameTextView.text = "End Point: ${locationInfo.endPoint.endPointName}"
        locationHolder.distanceTextView.text = "Distance: ${locationInfo.distance}"
        locationHolder.timestampTextView.text = "Timestamp: ${locationInfo.timestamp}"
    }

    override fun getItemCount(): Int {
        return locationInfoList.size
    }
}