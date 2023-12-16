package com.example.salesdesign.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddCustomer : Fragment() {

    private lateinit var submitbtn: Button
    private lateinit var nameEdt: TextInputLayout
    private lateinit var emailEdt:TextInputLayout
    private lateinit var addressEdt: TextInputLayout
    private lateinit var industryTypeEdt: TextInputLayout
    private lateinit var phoneEdt: TextInputLayout
    private lateinit var reasonEdt: TextInputLayout
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiService: ApiService

    private var userId: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_add_customer, container, false)

        nameEdt = view.findViewById(R.id.customerNameID)
        emailEdt = view.findViewById(R.id.customerEmailID)
        addressEdt = view.findViewById(R.id.customerAddressID)
        industryTypeEdt = view.findViewById(R.id.customerIndustryTypeID)
        phoneEdt = view.findViewById(R.id.customerPhoneID)
        reasonEdt = view.findViewById(R.id.customerReasonID)

        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        apiService = RetrofitClient.getClient().create(ApiService::class.java)


        submitbtn = view.findViewById(R.id.addCustomerBtn)
        submitbtn.setOnClickListener {
            AddCustomerData()
        }

        return view
    }

    private fun AddCustomerData(){

        val customerName = nameEdt.editText!!.text.toString()
        val customerEmail = emailEdt.editText!!.text.toString()
        val customerAddress = addressEdt.editText!!.text.toString()
        val customerPhone = phoneEdt.editText!!.text.toString()
        val industryType = industryTypeEdt.editText!!.text.toString()
        val reason = reasonEdt.editText!!.text.toString()

        val newCustomer = Customer(
            customerName,
            customerPhone ,
            customerEmail,
            customerAddress ,
            industryType ,
            reason
        )

        Log.d("---------", "AddCustomerData: ${newCustomer}")

// Make the API request to save the customer data
        val call = apiService.saveCustomer(newCustomer)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                  Toast.makeText(requireContext(),"Successfully Added Data" ,  Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(),"Fail to Added Data" ,  Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(requireContext(),"Network Error" ,  Toast.LENGTH_SHORT).show()
            }
        })
    }
}

data class Customer(
    val customerName: String,
    val customerPhone: String,
    val customerEmail:String,
    val customerAddress: String,
    val industryType: String,
    val reason: String
)

