package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Adapter.ListAdapter
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R

class KMSActivity : AppCompatActivity() {

    private lateinit var backbtn : ImageView

    private lateinit var showCardDetails: RecyclerView
    private lateinit var listAdapter: ListAdapter

    private lateinit var todayKm : LinearLayout
    private lateinit var weekKm : LinearLayout
    private lateinit var monthKm : LinearLayout
    private lateinit var yearKm : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kmsactivity)


        todayKm = findViewById(R.id.todayKmLy)
        weekKm = findViewById(R.id.weekKmLy)
        monthKm = findViewById(R.id.monthKmLy)
        yearKm = findViewById(R.id.yearKmLy)


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@KMSActivity, MainActivity::class.java)
            startActivity(intent)
        }
            showCardDetails = findViewById(R.id.showCardDetails)
            listAdapter = ListAdapter()

            // Set up RecyclerView with a LinearLayoutManager
            showCardDetails.layoutManager = LinearLayoutManager(this)
            showCardDetails.adapter = listAdapter

            // Set up click listeners for different cards
            todayKm.setOnClickListener {
                onCardClick(getTodayList())
            }

            weekKm.setOnClickListener {
                onCardClick(getWeeklyList())
            }

            monthKm.setOnClickListener {
                onCardClick(getMonthlyList())
            }

            yearKm.setOnClickListener {
                onCardClick(getYearlyList())
            }
        }

        private fun onCardClick(listItems: List<ListItem>) {
            // Update the adapter with the clicked list items
            listAdapter.submitList(listItems)

            // Make the showCardLY layout visible
            val showCardLY: LinearLayout = findViewById(R.id.showCardLY)
            showCardLY.visibility = View.VISIBLE
        }

        private fun getTodayList(): List<ListItem> {
            // Logic to retrieve today's list items
            return listOf(
                ListItem("Item 1", "Value 1"),
                ListItem("Itemfgdfgfd", "Value 2"),
                // Add more items as needed
            )
        }

        private fun getWeeklyList(): List<ListItem> {
            return listOf(
                ListItem("Item --------------", "Value 1"),
                ListItem("Item 2", "Value 2"),
                // Add more items as needed
            )
        }

        private fun getMonthlyList(): List<ListItem> {
            return listOf(
                ListItem("Item 1================", "Value 1"),
                ListItem("Item 2", "Value 2"),
                // Add more items as needed
            )
        }

        private fun getYearlyList(): List<ListItem> {
            return listOf(
                ListItem("Item 14444444444444444", "Value 1"),
                ListItem("Item 2", "Value 2"),
                // Add more items as needed
            )
        }
}



data class ListItem(val itemName: String, val itemValue: String)