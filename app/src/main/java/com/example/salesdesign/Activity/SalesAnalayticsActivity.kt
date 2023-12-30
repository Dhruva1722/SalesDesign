package com.example.salesdesign.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class SalesAnalayticsActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_analaytics)

        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@SalesAnalayticsActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}