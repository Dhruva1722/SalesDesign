package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.CustomerAdapter
import com.example.salesdesign.Fragment.ItemsViewModel

import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class CustomerActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)



        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@CustomerActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val recyclerview = findViewById<RecyclerView>(R.id.customerList)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..10) {
            data.add(ItemsViewModel( "1 " + i , "Nick Jhons" + i , "vatva GIDC  Ahmedabad"+ i , "I visit this customer to this reason"))
        }

        val adapter = CustomerAdapter(data)

        recyclerview.adapter = adapter


    }


}

