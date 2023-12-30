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
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.LocationAdapter
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeekKmFragment : Fragment() {

    private lateinit var locationAdapter: LocationAdapter
    private val locations: MutableList<LocationInformation> = mutableListOf()

    private lateinit var sharedPreferences: SharedPreferences
    private var userId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_week_km, container, false)

//        val recyclerView: RecyclerView = view.findViewById(R.id.weekList)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("User", null) ?: ""
        Log.d("--------", "Response:  $userId")

        val apiService = RetrofitClient.getClient().create(ApiService::class.java)

//        val call = apiService.getLocationInfo(userId!!)
//        call.enqueue(object : Callback<List<LocationResponse>> {
//            override fun onResponse(call: Call<List<LocationResponse>>, response: Response<List<LocationResponse>>) {
//                if (response.isSuccessful) {
//                    val locationInfoList = response.body()
//                    Log.d("--------", "Response:  $locationInfoList")
//                    locationInfoList?.let {
//                        val recyclerView: RecyclerView = view.findViewById(R.id.weekList)
//                        locationAdapter = LocationAdapter(emptyList())
//                        recyclerView.adapter = locationAdapter
//                    }
//                } else {
//                    Toast.makeText(requireContext(), "fail to get data ", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<LocationResponse>>, t: Throwable) {
//                // Handle network failure
//                Log.e("-----", "onFailure: ${t.message}")
//                Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
//            }
//        })




        return view
    }

}

//private fun <T> Call<T>.enqueue(callback: Callback<List<T>>) {
//    TODO("Not yet implemented")
//}



