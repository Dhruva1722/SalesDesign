package com.example.salesdesign.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Activity.ListItem
import com.example.salesdesign.R

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var listItems: List<ListItem> = emptyList()

    fun submitList(newList: List<ListItem>) {
        listItems = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.today_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName: TextView = itemView.findViewById(R.id.itemName)
        private val itemValue: TextView = itemView.findViewById(R.id.itemValue)

        fun bind(item: ListItem) {
            itemName.text = item.itemName
            itemValue.text = item.itemValue
        }
    }
}