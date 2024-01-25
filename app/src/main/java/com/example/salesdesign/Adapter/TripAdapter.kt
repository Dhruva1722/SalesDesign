package com.example.salesdesign.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Activity.LocationInfo
import com.example.salesdesign.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class TripAdapter(private val context: Context, private val tripList: List<LocationInfo>) :
    RecyclerView.Adapter<TripAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.tripdetail, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trip = tripList[position]

        // Update the date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

        try {
            val timestamp = dateFormat.parse(trip.timestamp)
            val formattedDate =
                SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault()).format(timestamp)

            // Display start and end points
            val startPoint =
                "${trip.startPoint.startPointname}"
            val endPoint =
                "${trip.endPoint.endPointname} "

            // Display distance with two decimal places
            val formattedDistance = "${trip.distance}"

            holder.tripDateTextView.text = formattedDate
            holder.tripStartTextView.text = startPoint
            holder.tripEndTextView.text = endPoint
            holder.tripDistanceTextView.text = formattedDistance
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = tripList.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tripDateTextView: TextView = itemView.findViewById(R.id.tripDate)
        val tripStartTextView: TextView = itemView.findViewById(R.id.tripStart)
        val tripEndTextView: TextView = itemView.findViewById(R.id.tripEnd)
        val tripDistanceTextView: TextView = itemView.findViewById(R.id.tripDistance)
    }
}