package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.CustomerAdapter
import com.example.salesdesign.Fragment.Customer


import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView
    private lateinit var apiService: ApiService

    private lateinit var recyclerView: RecyclerView
    private lateinit var customerAdapter: CustomerAdapter
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)

        apiService = RetrofitClient.getClient().create(ApiService::class.java)


        recyclerView = findViewById(R.id.customerList)
        recyclerView.layoutManager = LinearLayoutManager(this@CustomerActivity)
        fetchDataFromApi()


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@CustomerActivity, MainActivity::class.java)
            startActivity(intent)
        }


        searchView = findViewById(R.id.idSV)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                customerAdapter.filterData(newText.orEmpty())
                return true
            }
        })



    }


    private fun fetchDataFromApi() {
        val call = apiService.getCustomers()
        call.enqueue(object : Callback<List<Customer>> {
            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                if (response.isSuccessful) {
                    val customers = response.body()
                    customers?.let {
                        customerAdapter = CustomerAdapter(it)
                        recyclerView.adapter = customerAdapter

                        customerAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                Toast.makeText(this@CustomerActivity, "No Data available", Toast.LENGTH_SHORT).show()
            }
        })

    }



}

