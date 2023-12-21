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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visits)

        viewPager = findViewById(R.id.ViewPager)
        tabLayout = findViewById(R.id.tabLayout)

        adapter = MenuPagerAdapter(this)
        viewPager.adapter = adapter



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