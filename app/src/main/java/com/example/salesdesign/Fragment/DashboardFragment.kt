package com.example.salesdesign.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.salesdesign.Activity.CustomerActivity
import com.example.salesdesign.Activity.ExpenseActivity
import com.example.salesdesign.Activity.KMSActivity
import com.example.salesdesign.Activity.ProfileActivity
import com.example.salesdesign.Activity.ReadyStockActivity
import com.example.salesdesign.Activity.SalesAnalayticsActivity
import com.example.salesdesign.Activity.VisitsActivity
import com.example.salesdesign.Map.MapsActivity
import com.example.salesdesign.R


class DashboardFragment : Fragment() {
     private lateinit var expenseActivity: RelativeLayout
     private lateinit var kmsActivity: RelativeLayout
     private lateinit var visitsActivity: RelativeLayout
     private lateinit var mapsActivity: RelativeLayout
     private lateinit var stockActivity: RelativeLayout
     private lateinit var customerActivity: RelativeLayout
     private lateinit var salesActivity: RelativeLayout
     private lateinit var profileActivity: RelativeLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        expenseActivity = view.findViewById(R.id.expenseLy)
        kmsActivity = view.findViewById(R.id.kmsLY)
        visitsActivity = view.findViewById(R.id.visitingLY)
        customerActivity = view.findViewById(R.id.customerLY)
        mapsActivity = view.findViewById(R.id.mapLy)
        stockActivity = view.findViewById(R.id.readStockLY)
        salesActivity = view.findViewById(R.id.salesLy)
        profileActivity = view.findViewById(R.id.profileLY)

        expenseActivity.setOnClickListener {
            val intent = Intent(requireContext(),ExpenseActivity::class.java)
            startActivity(intent)
        }

        kmsActivity.setOnClickListener {
            val intent = Intent(requireContext(),KMSActivity::class.java)
            startActivity(intent)
        }
        visitsActivity.setOnClickListener {
            val intent = Intent(requireContext(),VisitsActivity::class.java)
            startActivity(intent)
        }

        customerActivity.setOnClickListener {
            val intent = Intent(requireContext(),CustomerActivity::class.java)
            startActivity(intent)
        }

        mapsActivity.setOnClickListener {
            val intent = Intent(requireContext(), MapsActivity::class.java)
            startActivity(intent)
        }

        stockActivity.setOnClickListener {
            val intent = Intent(requireContext(),ReadyStockActivity::class.java)
            startActivity(intent)
        }

        salesActivity.setOnClickListener {
            val intent = Intent(requireContext(), SalesAnalayticsActivity::class.java)
            startActivity(intent)
        }

        profileActivity.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}