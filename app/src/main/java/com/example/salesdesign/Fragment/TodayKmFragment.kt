package com.example.salesdesign.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.LocationAdapter
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class TodayKmFragment : Fragment() {

    private lateinit var locationAdapter: LocationAdapter
    private val locations: MutableList<LocationInfo> = mutableListOf()

    private lateinit var sharedPreferences: SharedPreferences
    private var userId: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_today_km, container, false)


        val recyclerView: RecyclerView = view.findViewById(R.id.todayList)
        locationAdapter = LocationAdapter(locations)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = locationAdapter


        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("User", null) ?: ""
        Log.d("--------", "Response:  $userId")

        val apiService = RetrofitClient.getClient().create(ApiService::class.java)

        val call = apiService.getLocation(userId!!)
        Log.d("TodayKmFragment", "Response: $call  $userId")

        call.enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                if (response.isSuccessful) {
                    val locationResponse = response.body()

                    // Log the entire response for debugging
                    Log.d("============================", "Response: $locationResponse")

                    if (locationResponse != null && locationResponse.status == "Success") {
                        val locationInfoList = locationResponse.locationInfoList
//                        locations.clear() // Clear the existing data
                        locations.addAll(locationInfoList) // Add the new data
                        locationAdapter.notifyDataSetChanged() // Notify the adapter of the data change

                        // Log the data for debugging
                        for (locationInfo in locationInfoList) {
                            Log.d("TodayKmFragment", "Start Point Name: ${locationInfo.startPoint.startPointName}")
                            Log.d("TodayKmFragment", "End Point Name: ${locationInfo.endPoint.endPointName}")
                            Log.d("TodayKmFragment", "Distance: ${locationInfo.distance}")
                        }


                    }
                } else {
                    // Handle unsuccessful response
                    Log.e("TodayKmFragment", "Unsuccessful response: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                // Handle failure
            }
        })

        return view
    }

}



data class LocationResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val locationInfoList: List<LocationInfo>
)

data class LocationInfo(
    @SerializedName("startPoint") val startPoint: Point,
    @SerializedName("endPoint") val endPoint: Point,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("distance") val distance: Double
)

data class Point(
    @SerializedName("startPointname") val startPointName: String,
    @SerializedName("startLatitude") val startLatitude: String,
    @SerializedName("startLongitude") val startLongitude: String,
    @SerializedName("endPointname") val endPointName: String,
    @SerializedName("endLatitude") val endLatitude: String,
    @SerializedName("endLongitude") val endLongitude: String
)