package com.example.salesdesign.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.TripAdapter
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripDetails : AppCompatActivity() {


    private lateinit var backbtn : ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private var userId: String? = null

    private lateinit var tripAdapter: TripAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_details)


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@TripDetails, ProfileActivity::class.java)
            startActivity(intent)
        }

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("User", null)

        val tripDetailsListView: RecyclerView = findViewById(R.id.tripDetailList)
        tripDetailsListView.layoutManager = LinearLayoutManager(this)

        val apiService = RetrofitClient.getClient().create(ApiService::class.java)
        val call = apiService.getTripData(userId!!)

        Log.d("]]]]]]", "onCreate: $call")

        call.enqueue(object : Callback<TripInfo> {
            override fun onResponse(call: Call<TripInfo>, response: Response<TripInfo>) {
                if (response.isSuccessful) {
                    val tripInfo: TripInfo? = response.body()
                    Log.d("/-/-/-/-/-/", "onCreate: $tripInfo  ----  $response")

                    // Check if TripInfo and Message are not null
                    tripInfo?.message?.Location_info?.let {
                        tripAdapter = TripAdapter(this@TripDetails, it)
                        tripDetailsListView.adapter = tripAdapter
                    }
                } else {
                    // Handle unsuccessful response
                    Toast.makeText(this@TripDetails, "Failed to fetch location info", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TripInfo>, t: Throwable) {
                // Handle network failure
                Toast.makeText(this@TripDetails, "Network error", Toast.LENGTH_SHORT).show()
                Log.d("\\\\\\\\\\", "onFailure: $t")
            }
        })
    }
}



data class TripInfo(
    val status: String,
    val message: Message? // Message is another data class
)
data class Message(
    val Location_info: List<LocationInfo>
)

data class LocationInfo(
    val startPoint: StartPoint,
    val endPoint: EndPoint,
    val distance: Double,
    val timestamp: String,
)

data class StartPoint(
    val startPointname: String,
    val startLatitude: String,
    val startLongitude: String
)

data class EndPoint(
    val endPointname: String,
    val endLatitude: String,
    val endLongitude: String
)