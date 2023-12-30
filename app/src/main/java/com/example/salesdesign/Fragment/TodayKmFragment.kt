package com.example.salesdesign.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class TodayKmFragment : Fragment() {

    private lateinit var locationAdapter: LocationAdapter
    private val locations: MutableList<LocationInformation> = mutableListOf()

    private lateinit var sharedPreferences: SharedPreferences
    private var userId: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_today_km, container, false)


//        val recyclerView: RecyclerView = view.findViewById(R.id.todayList)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("User", null) ?: ""
        Log.d("--------", "Response:  $userId")

        val apiService = RetrofitClient.getClient().create(ApiService::class.java)

//        val call = apiService.getLocationInfo(userId!!)
//        call.enqueue(object : Callback<LocationResponse> {
//            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
//                if (response.isSuccessful) {
//                    val locationResponse = response.body()
//                    locationResponse?.let {
//                        // Assuming LocationAdapter accepts a List<LocationInformation>
//                        val locationInfoList = listOf(it.locationInfo)
//                        locationAdapter = LocationAdapter(locationInfoList)
//                        recyclerView.adapter = locationAdapter
//                    }
//                } else {
//                    Toast.makeText(requireContext(), "No Data available", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
//                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
//                Log.d("-----", "onFailure: $t")
//            }
//        })


        return view
    }

}




data class LocationResponse(
    @SerializedName("Location_info")
    val locationInfo: LocationInformation
)

data class LocationInformation(
    val startPoint: StartPoint,
    val endPoint: EndPoint,
    val distance: Double,
    val timestamp: Long
)

data class StartPoint(
    val startPointName: String,
    val startLatitude: String,
    val startLongitude: String
)

data class EndPoint(
    val endPointName: String,
    val endLatitude: String,
    val endLongitude: String
)