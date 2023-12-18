package com.example.salesdesign.Adapter

import android.location.Location
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Fragment.LocationInfo
import com.example.salesdesign.R

class LocationAdapter(private val locationList: List<LocationInfo>) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startPointNameTextView: TextView = itemView.findViewById(R.id.startPoint)
        val endPointNameTextView: TextView = itemView.findViewById(R.id.endPoint)
        val distanceTextView: TextView = itemView.findViewById(R.id.distance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentLocation = locationList[position]

        holder.startPointNameTextView.text = currentLocation.startPoint.startPointName
        holder.endPointNameTextView.text = currentLocation.endPoint.endPointName
        holder.distanceTextView.text = currentLocation.distance.toString()
    }

    override fun getItemCount(): Int {
        return locationList.size
    }
}