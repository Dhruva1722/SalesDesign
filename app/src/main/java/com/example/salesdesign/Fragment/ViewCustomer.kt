package com.example.salesdesign.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.CustomerAdapter
//import com.example.salesdesign.Adapter.CustomerAdapter
import com.example.salesdesign.R

class ViewCustomer : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_view_customer, container, false)

        val recyclerview = view.findViewById<RecyclerView>(R.id.viewList)
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        val data = ArrayList<ItemsViewModel>()

        for (i in 1..10) {
            data.add(ItemsViewModel( "1 " + i , "Nick Jhons" + i , "vatva GIDC  Ahmedabad"+ i , "I visit this customer to this reason"))
        }

        val adapter = CustomerAdapter(data)

        recyclerview.adapter = adapter


        return view
    }



}
data class ItemsViewModel(
    val customerID : String,
    val Name : String,
    val location : String,
    val information : String
)