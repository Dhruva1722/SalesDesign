package com.example.salesdesign.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComplaintActivity : AppCompatActivity() {

    private lateinit var  edtMsgInput: TextInputEditText
    private lateinit var submitButton: Button

    private lateinit var backbtn : ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private var userId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("User", null) ?: ""

        edtMsgInput = findViewById(R.id.msg_input)
        submitButton = findViewById(R.id.submit_btn)

        submitButton.setOnClickListener {
            submitComplaint();
        }


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@ComplaintActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun submitComplaint() {

        val complaintMessage = edtMsgInput.text.toString()

        if (complaintMessage.isNotEmpty() && userId != null) {
            val apiService = RetrofitClient.getClient().create(ApiService::class.java)

            val requestBody = ComplaintRequest(userId!!, complaintMessage)
            Log.d("==================", "submitComplaint: employee complain  ${requestBody}")
            apiService.submitComplaint(requestBody).enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Complaint submitted successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Failed to submit complaint", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    Toast.makeText(applicationContext, "Network error", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(applicationContext, "Please enter a complaint message", Toast.LENGTH_SHORT).show()
        }
    }

}
data class ComplaintRequest(
    val userId: String,
    val message: String
)
