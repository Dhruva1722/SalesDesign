package com.example.salesdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.salesdesign.Fragment.AttendanceFragment
import com.example.salesdesign.Fragment.CanteenFragment
import com.example.salesdesign.Fragment.DashboardFragment
import com.example.salesdesign.Fragment.EventFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        loadFragment(DashboardFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        if (item.itemId == R.id.dashboard) {
            fragment = DashboardFragment()
        }  else if (item.itemId == R.id.canteen) {
            fragment = CanteenFragment()
        } else if (item.itemId == R.id.event) {
            fragment = EventFragment()
        }
        fragment?.let { loadFragment(it) }
        return true
    }

    fun loadFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction().replace(R.id.relativelayout, fragment!!).commit()
    }
}
//else if (item.itemId == R.id.attendance) {
//    fragment = AttendanceFragment()
//}


