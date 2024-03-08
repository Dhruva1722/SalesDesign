package com.example.salesdesign.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Activity.Employee
import com.example.salesdesign.Activity.TargetData
import com.example.salesdesign.R

class TargetListAdapter (private val dataList: List<TargetData>) : RecyclerView.Adapter<TargetListAdapter.TargetListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.target_data_table, parent, false)
        return TargetListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TargetListViewHolder, position: Int) {
        val employee = dataList[position]
        holder.bind(employee)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class TargetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.instrumentType)
        private val textViewContact: TextView = itemView.findViewById(R.id.stockNo)
        private val textViewDesignation: TextView = itemView.findViewById(R.id.targetSummary)

        fun bind(employee: TargetData) {
            textViewName.text = employee.InstrumentType
            textViewContact.text = employee.Stock
            textViewDesignation.text = employee.Target
        }
    }

}