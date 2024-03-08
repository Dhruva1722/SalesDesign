package com.example.salesdesign.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.EmployeeAdapter
import com.example.salesdesign.Adapter.TargetListAdapter
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class SalesAnalayticsActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var targetListAdapter: TargetListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_analaytics)

        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@SalesAnalayticsActivity, MainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.analysis_data_table)

        // Create an array of Employee objects
        val employeeList = mutableListOf<TargetData>()
        employeeList.add(TargetData("John Doe", "1234567890", "Manager"))
        employeeList.add(TargetData("Jane Smith", "0987654321", "Supervisor"))
        employeeList.add(TargetData("Jane Smith", "0987654321", "Supervisor"))
        employeeList.add(TargetData("Jane Smith", "0987654321", "Supervisor"))
        employeeList.add(TargetData("Jane Smith", "0987654321", "Supervisor"))
        employeeList.add(TargetData("Jane Smith", "0987654321", "Supervisor"))
        employeeList.add(TargetData("Jane Smith", "0987654321", "Supervisor"))
        // Add more employees as needed

        // Initialize the adapter with the array of Employee objects
        targetListAdapter = TargetListAdapter(employeeList)

        // Set the layout manager and adapter for the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = targetListAdapter
    }

    }

data class TargetData(
    val InstrumentType: String,
    val Stock: String,
    val Target: String,
)
