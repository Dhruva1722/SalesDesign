package com.example.salesdesign.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.MainActivity
import com.example.salesdesign.R
import com.example.salesdesign.Retrofit.ApiService
import com.example.salesdesign.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.salesdesign.Adapter.CustomizedExpandableListAdapter


class ReadyStockActivity : AppCompatActivity() {

    private lateinit var expandableListViewExample: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var backbtn: ImageView

    private lateinit var adapter: CustomizedExpandableListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ready_stock)



        backbtn = findViewById(R.id.backArrow)
        backbtn.setOnClickListener {
            val intent = Intent(this@ReadyStockActivity, MainActivity::class.java)
            startActivity(intent)
        }

        expandableListViewExample = findViewById(R.id.expandableListViewSample)
        searchView = findViewById(R.id.idSV)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filterData(newText.orEmpty())
                return true
            }
        })


        val recyclerView: RecyclerView = findViewById(R.id.expandableListViewSample)
        val apiService = RetrofitClient.getClient().create(ApiService::class.java)
        val call: Call<List<StockResponse>> = apiService.getStockData()
        call.enqueue(object : Callback<List<StockResponse>> {
            override fun onResponse(call: Call<List<StockResponse>>, response: Response<List<StockResponse>>) {
                if (response.isSuccessful) {
                    val data: List<StockResponse>? = response.body()

                    data?.let {
                        // Use the retrieved data to populate the RecyclerView
                         adapter = CustomizedExpandableListAdapter(it)
                        recyclerView.layoutManager = LinearLayoutManager(this@ReadyStockActivity)
                        recyclerView.adapter = adapter
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<StockResponse>>, t: Throwable) {
                // Handle network failure
            }
        })
    }


    }




data class StockResponse(
    val Emp_ID: String,
    val Emp_name: String,
    val Emp_department: String,
    val Emp_country: String,
    val Emp_blood_group: String,
    val Emp_state: String,
    var isExpanded: Boolean = false
)
















//        expandableDetailList = ExpandableListDataItems.getData()
//        expandableTitleList = ArrayList(expandableDetailList.keys)
//        expandableListAdapter =
//            CustomizedExpandableListAdapter(this, expandableTitleList, expandableDetailList)
//        expandableListViewExample.setAdapter(expandableListAdapter)
//
//        // This method is called when the group is expanded
//        expandableListViewExample.setOnGroupExpandListener { groupPosition ->
//            Toast.makeText(
//                applicationContext,
//                "${expandableTitleList[groupPosition]} List Expanded.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
//        // This method is called when the group is collapsed
//        expandableListViewExample.setOnGroupCollapseListener { groupPosition ->
//            Toast.makeText(
//                applicationContext,
//                "${expandableTitleList[groupPosition]} List Collapsed.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//
//
//        expandableListViewExample.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
//            Toast.makeText(
//                applicationContext,
//                "${expandableTitleList[groupPosition]} -> ${expandableDetailList[expandableTitleList[groupPosition]]?.get(
//                    childPosition
//                )}",
//                Toast.LENGTH_SHORT
//            ).show()
//            false
//        }
//    }
//}
//
//object ExpandableListDataItems {
//    fun getData(): HashMap<String, List<String>> {
//        val expandableDetailList = HashMap<String, List<String>>()
//
//        // As we are populating List of stock1, stock2, and stock3, using them here
//        // We can modify them as per our choice.
//        // And also the choice of stock1/stock2/stock3 can be changed
//        val stock1 = listOf(" No: 1878", " MFG Section : Temperature Controller", " Category: PID Temperature Controller", "Model: UTC-114", "Product: UTC-114-A2-R"," Stock : 709")
//
//        val stock2 = listOf(" No: 338", " MFG Section : Timer&Counter", " Category: Count Totalizer", "Model: CT-3000", "Product:  CT-3000-B1-02"," Stock : 646")
//
//        val stock3 = listOf(" No: 1940", " MFG Section : Temperature Controller", " Category: PID Temperature Controller", "Model: UTC-413P", "Product: UTC-413P-A2-R"," Stock : 640")
//
//        val stock4 = listOf(" No: 1986", " MFG Section : Temperature Controller", " Category: PID Temperature Controller", "Model: UTC-413P", "Product: UTC-413P-A2-R"," Stock : 577")
//
//        val stock5 = listOf(" No: 1929", " MFG Section : Temperature Controller", " Category: PID Temperature Controller", "Model: UTC-413P", "Product: UTC-413P-A2-R"," Stock : 445")
//
//        val stock6 = listOf(" No: 1792", " MFG Section : Temperature Controller", " Category: PID Temperature Controller", "Model: UTC-413P", "Product: UTC-413P-A2-R"," Stock : 363")
//
//        val stock7 = listOf(" No: 656", " MFG Section : Temperature Controller", " Category: PID Temperature Controller", "Model: UTC-413P", "Product: UTC-413P-A2-R"," Stock : 357")
//        // stock1 are grouped under stock1 Items. Similarly, the rest two are under
//        // Vegetable Items and stock3 Items, respectively.
//        // i.e. expandableDetailList object is used to map the group header strings to
//        // their respective children using an ArrayList of Strings.
//        expandableDetailList["No:1878  MFG Section:Temperature Controller   Stock:709"] = stock1
//        expandableDetailList["338  Timer & Counter  646"] = stock2
//        expandableDetailList["1940  Temperature Controller  640"] = stock3
//        expandableDetailList["1986  Temperature Controller  577"] = stock4
//        expandableDetailList["1929  Temperature Controller  445"] = stock5
//        expandableDetailList["1792  Temperature Controller  363"] = stock6
//        expandableDetailList["656  Temperature Controller  357"] = stock7
//        return expandableDetailList
//    }