package com.example.salesdesign.Activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var tripActivity: TextView
    private lateinit var leaveActivity: TextView
    private lateinit var bankDetailsActivity: TextView
    private lateinit var basicInfoActivity: TextView
    private lateinit var companyPropertyActivity: TextView
    private lateinit var logout: TextView

    private lateinit var backbtn : ImageView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(intent)
        }

        tripActivity = findViewById(R.id.trip_details)
        leaveActivity = findViewById(R.id.leave_details)
        bankDetailsActivity= findViewById(R.id.bank_Details)
        basicInfoActivity = findViewById(R.id.basic_information)
        companyPropertyActivity = findViewById(R.id.company_property)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        tripActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity, TripDetails::class.java)
            startActivity(intent)
        }

        leaveActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity, LeaveStatus::class.java)
            startActivity(intent)
        }

        bankDetailsActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity, BankDetailsActivity::class.java)
            startActivity(intent)
        }

        basicInfoActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity, BasicInfoActivity::class.java)
            startActivity(intent)
        }

        companyPropertyActivity.setOnClickListener {
            val intent = Intent(this@ProfileActivity, CompanyAssetsActivity::class.java)
            startActivity(intent)
        }

        logout= findViewById(R.id.logoutBtn)
        logout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("isLoggedIn")
            editor.remove("User")
            editor.remove("userEmail")
            editor.remove("ATTENDANCE_STATUS_KEY")
            editor.remove("LAST_ATTENDANCE_DATE_KEY")
            editor.remove("DATE_KEY, DateTime")
            editor.apply()
            println("HERE --------------")
            finishAffinity()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}