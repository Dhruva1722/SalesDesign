package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.salesdesign.Fragment.MonthKmFragment
import com.example.salesdesign.Fragment.TodayKmFragment
import com.example.salesdesign.Fragment.WeekKmFragment
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class KMSActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView

    private lateinit var showCardDetails: FrameLayout


    private lateinit var todayKm : LinearLayout
    private lateinit var weekKm : LinearLayout
    private lateinit var monthKm : LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kmsactivity)


        todayKm = findViewById(R.id.todayKmLy)
        weekKm = findViewById(R.id.weekKmLy)
        monthKm = findViewById(R.id.monthKmLy)



        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@KMSActivity, MainActivity::class.java)
            startActivity(intent)
        }
            showCardDetails = findViewById(R.id.showCardDetails)


//            showCardDetails.layoutManager = LinearLayoutManager(this)


        val fragmentManager = supportFragmentManager

        // Set up click listeners for different cards
            todayKm.setOnClickListener {
                val todayFragment = TodayKmFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.showCardDetails, todayFragment)
                    .addToBackStack(null)
                    .commit()
            }

            weekKm.setOnClickListener {
                val weekFragment = WeekKmFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.showCardDetails, weekFragment)
                    .addToBackStack(null)
                    .commit()
            }

            monthKm.setOnClickListener {
                val monthFragment = MonthKmFragment()
                fragmentManager.beginTransaction()
                    .replace(R.id.showCardDetails, monthFragment)
                    .addToBackStack(null)
                    .commit()
            }


        }


}



