package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.salesdesign.R

class ProfileActivity : AppCompatActivity() {


    private lateinit var tripActivity: TextView
    private lateinit var leaveActivity: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        tripActivity = findViewById(R.id.trip_details)
        leaveActivity = findViewById(R.id.leave_details)


        tripActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity,TripDetails::class.java)
            startActivity(intent)
        }

        leaveActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity,LeaveStatus::class.java)
            startActivity(intent)
        }

    }
}