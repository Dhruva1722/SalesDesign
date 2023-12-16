package com.example.salesdesign.Fragment

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.CustomerAdapter
//import com.example.salesdesign.Adapter.CustomerAdapter
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewCustomer : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var customerAdapter: CustomerAdapter

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_customer, container, false)

        apiService = RetrofitClient.getClient().create(ApiService::class.java)


        recyclerView = view.findViewById(R.id.viewList)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        customerAdapter = CustomerAdapter(emptyList()) // Pass your customer data here
        recyclerView.adapter = customerAdapter

        fetchDataFromApi()

        return view
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
                    }
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                Toast.makeText(requireContext(),"No Data available" ,  Toast.LENGTH_SHORT).show()
            }
        })
    }


}
