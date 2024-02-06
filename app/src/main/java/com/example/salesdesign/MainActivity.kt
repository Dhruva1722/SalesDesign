package com.example.salesdesign

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.salesdesign.Activity.ComplaintActivity
import com.example.salesdesign.Activity.LoginActivity
import com.example.salesdesign.Fragment.AttendanceFragment
import com.example.salesdesign.Fragment.CanteenFragment
import com.example.salesdesign.Fragment.DashboardFragment
import com.example.salesdesign.Fragment.EventFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{


    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var helpBtn: ImageView
    private lateinit var logoutBtn: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        loadFragment(DashboardFragment())

        logoutBtn = findViewById(R.id.logoutBtn)
        logoutBtn.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("isLoggedIn")
            editor.remove("User")
            editor.remove("userEmail")
            editor.remove("ATTENDANCE_STATUS_KEY")
            editor.remove("LAST_ATTENDANCE_DATE_KEY")
            editor.remove("DATE_KEY, DateTime")
            editor.apply()
            println("HERE --------------")
            finishAffinity()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        helpBtn = findViewById(R.id.helpBtn)

        helpBtn.setOnClickListener { v ->
            showPopupMenu(v)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        if (item.itemId == R.id.dashboard) {
            fragment = DashboardFragment()
        } else if (item.itemId == R.id.attendance) {
            fragment = AttendanceFragment()
        }else if (item.itemId == R.id.canteen) {
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

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.help_menu)

        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_help -> {
                    val intent = Intent(this, HelpActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.action_complain -> {
                    val intent = Intent(this, ComplaintActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }
}
//else if (item.itemId == R.id.attendance) {
//    fragment = AttendanceFragment()
//}


