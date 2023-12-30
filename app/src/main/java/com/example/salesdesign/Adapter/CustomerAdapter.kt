package com.example.salesdesign.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Fragment.Customer
import com.example.salesdesign.R

class CustomerAdapter (private var customerList: List<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    private var originalCustomerList: List<Customer> = customerList.toList()

    inner class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.viewName)
        val phoneTextView: TextView = itemView.findViewById(R.id.viewPhone)
        val locationTextView: TextView = itemView.findViewById(R.id.viewLocation)
        val industryTypeTextView: TextView = itemView.findViewById(R.id.viewIndustryType)
        val informationTextView: TextView = itemView.findViewById(R.id.viewInformation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewcustomer_list, parent, false)
        return CustomerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentCustomer = customerList[position]
        holder.nameTextView.text = currentCustomer.customerName
        holder.phoneTextView.text = currentCustomer.customerPhone
        holder.locationTextView.text = currentCustomer.customerAddress
        holder.industryTypeTextView.text = currentCustomer.industryType
        holder.informationTextView.text = currentCustomer.reason
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    fun filterData(query: String) {
        customerList = if (query.isEmpty()) {
            originalCustomerList.toList()
        } else {
            originalCustomerList.filter { customer ->
                // Modify the conditions based on your search requirements
                customer.customerName.contains(query, true) ||
                        customer.customerPhone.contains(query, true) ||
                        customer.industryType.contains(query, true)
            }
        }
        notifyDataSetChanged()
    }

}