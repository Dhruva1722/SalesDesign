package com.example.salesdesign.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Fragment.ItemsViewModel
import com.example.salesdesign.R

class CustomerAdapter (private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewcustomer_list, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.idtv.text = ItemsViewModel.customerID
        holder.nametv.text = ItemsViewModel.Name
        holder.locationtv.text = ItemsViewModel.location
        holder.informationtv.text = ItemsViewModel.information
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val idtv: TextView = itemView.findViewById(R.id.customerID)
        val nametv : TextView = itemView.findViewById(R.id.customerName)
        val locationtv : TextView = itemView.findViewById(R.id.customerLocation)
        val informationtv : TextView = itemView.findViewById(R.id.customerImformation)
    }
}