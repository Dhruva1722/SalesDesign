package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class ExpenseActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@ExpenseActivity, MainActivity::class.java)
            startActivity(intent)
        }


    }
}