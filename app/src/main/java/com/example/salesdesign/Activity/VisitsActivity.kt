package com.example.salesdesign.Activity

import android.content.Intent
import android.database.MatrixCursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.ImageView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.salesdesign.Adapter.CustomerAdapter
import com.example.salesdesign.Fragment.AddCustomer
import com.example.salesdesign.Fragment.Customer
import com.example.salesdesign.Fragment.ViewCustomer
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class VisitsActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: MenuPagerAdapter
    private lateinit var backbtn : ImageView
    private lateinit var searchView: SearchView


    private lateinit var customerAdapter: CustomerAdapter
    private lateinit var customers: List<Customer>


    private val industrySuggestions = listOf("Stone cutting machines manufacturer", "Plastic processing & Post extrusion equipment", "Chiller manufacturer",
        "Paper Cup Making  Machine", "Stone cutting"," Panel Builder" , "Blood Bank Equipment, Covid lab Equipment , Hospital Furniture" ,
        "Blow Moulding Machine, Homeopathic Bottle Making machine , Plastic Gum Tube Making Machine etc"," The Hydraulic Company ", " Elevator manufacturer")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visits)

        viewPager = findViewById(R.id.ViewPager)
        tabLayout = findViewById(R.id.tabLayout)

        adapter = MenuPagerAdapter(this)
        viewPager.adapter = adapter



       searchView = findViewById(R.id.idSV)
        val from = arrayOf("industryName")
        val to = intArrayOf(android.R.id.text1)
        val suggestionAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
        searchView.suggestionsAdapter = suggestionAdapter

        // Add a text change listener to the search view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCustomers(newText)
                return true
            }
        })

        // Update the suggestions when the search view is expanded
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                // Handle suggestion click if needed
                return true
            }
        })


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Add Customer"
                1 -> tab.text = "View Customer"
            }
        }.attach()


        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@VisitsActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun filterCustomers(query: String?) {
        query?.let {
            val cursor = MatrixCursor(arrayOf(BaseColumns._ID, "industryName"))
            for ((index, suggestion) in industrySuggestions.withIndex()) {
                if (suggestion.contains(query, ignoreCase = true)) {
                    cursor.addRow(arrayOf(index, suggestion))
                }
            }
            (searchView.suggestionsAdapter as SimpleCursorAdapter).changeCursor(cursor)
        }
    }
}

class MenuPagerAdapter(fragment: VisitsActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AddCustomer()
            1 -> ViewCustomer()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}