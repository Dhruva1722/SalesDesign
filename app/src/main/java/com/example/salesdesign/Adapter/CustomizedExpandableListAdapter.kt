package com.example.salesdesign.Adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.salesdesign.Activity.StockResponse
import com.example.salesdesign.R

class CustomizedExpandableListAdapter (private var dataList: List<StockResponse>) :
    RecyclerView.Adapter<CustomizedExpandableListAdapter.ExpandableCardViewHolder>() {

    private var originalDataList: List<StockResponse> = dataList.toList()

    class ExpandableCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerLayout: LinearLayout = itemView.findViewById(R.id.headerLayout)
        val expandableLayout: LinearLayout = itemView.findViewById(R.id.expandableLayout)
        val listTitle: TextView = itemView.findViewById(R.id.listTitle)
        val viewNo: TextView = itemView.findViewById(R.id.viewNo)
        val viewMFG: TextView = itemView.findViewById(R.id.viewMFG)
        val viewCategory: TextView = itemView.findViewById(R.id.viewCategory)
        val viewModel: TextView = itemView.findViewById(R.id.viewModel)
        val viewProduct: TextView = itemView.findViewById(R.id.viewProduct)
        val viewStock: TextView = itemView.findViewById(R.id.viewStock)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrowIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpandableCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expandable_card_layout, parent, false)
        return ExpandableCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpandableCardViewHolder, position: Int) {
        val dataItem = dataList[position]


        // Bind your data to the views in the holder
        holder.listTitle.text = " No: ${dataItem.Emp_ID} ,  MFG Section : ${dataItem.Emp_name} ,  Stock : ${dataItem.Emp_state}"
        holder.viewNo.text =  " MFG Section : ${dataItem.Emp_name}"
        holder.viewMFG.text =  " Category: ${dataItem.Emp_department}"
        holder.viewCategory.text = "Model: ${dataItem.Emp_country}"
        holder.viewModel.text ="Product: ${dataItem.Emp_blood_group}"
        holder.viewProduct.text = " Stock : ${dataItem.Emp_state}"
        holder.viewStock.text =  " Stock : ${dataItem.Emp_state}"


        if (dataItem.isExpanded) {
            holder.arrowIcon.setImageResource(R.drawable.baseline_arrow_drop_up_24)
        } else {
            holder.arrowIcon.setImageResource(R.drawable.baseline_arrow_drop_down_24)
        }

        holder.headerLayout.setOnClickListener {
            if (holder.expandableLayout.visibility == View.VISIBLE) {
                holder.expandableLayout.visibility = View.GONE
                dataItem.isExpanded = false
                holder.arrowIcon.setImageResource(R.drawable.baseline_arrow_drop_down_24)
            } else {
                holder.expandableLayout.visibility = View.VISIBLE
                dataItem.isExpanded = true
                holder.arrowIcon.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun filterData(query: String) {
        dataList = if (query.isEmpty()) {
            originalDataList.toList()
        } else {
            originalDataList.filter { dataItem ->
                // Modify the conditions based on your search requirements
                dataItem.Emp_ID.contains(query, true) ||
                        dataItem.Emp_name.contains(query, true) ||
                        dataItem.Emp_department.contains(query, true) ||
                        dataItem.Emp_country.contains(query, true) ||
                        dataItem.Emp_blood_group.contains(query, true) ||
                        dataItem.Emp_state.contains(query, true)
            }
        }
        notifyDataSetChanged()
    }

}


//    override fun getChild(groupPosition: Int, childPosition: Int): Any {
//        return expandableDetailList[expandableTitleList[groupPosition]]?.get(childPosition) ?: ""
//    }
//
//    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
//        return childPosition.toLong()
//    }
//
//    override fun getChildView(
//        groupPosition: Int,
//        childPosition: Int,
//        isLastChild: Boolean,
//        convertView: View?,
//        parent: ViewGroup?
//    ): View {
//        var convertedView = convertView
//        val expandedListText = getChild(groupPosition, childPosition) as String
//
//        if (convertedView == null) {
//            val inflater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            convertedView = inflater.inflate(R.layout.stock_details, null)
//        }
//
//        val expandedListTextView: TextView =
//            convertedView?.findViewById(R.id.expandedListItem) as TextView
//        expandedListTextView.text = expandedListText
//
//        return convertedView
//    }
//
//    override fun getChildrenCount(groupPosition: Int): Int {
//        return expandableDetailList[expandableTitleList[groupPosition]]?.size ?: 0
//    }
//
//    override fun getGroup(groupPosition: Int): Any {
//        return expandableTitleList[groupPosition]
//    }
//
//    override fun getGroupCount(): Int {
//        return expandableTitleList.size
//    }
//
//    override fun getGroupId(groupPosition: Int): Long {
//        return groupPosition.toLong()
//    }
//
//    override fun getGroupView(
//        groupPosition: Int,
//        isExpanded: Boolean,
//        convertView: View?,
//        parent: ViewGroup?
//    ): View {
//        var convertedView = convertView
//        val listTitle = getGroup(groupPosition) as String
//
//        if (convertedView == null) {
//            val inflater =
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            convertedView = inflater.inflate(R.layout.stock_title, null)
//        }
//
//        val listTitleTextView: TextView =
//            convertedView?.findViewById(R.id.listTitle) as TextView
//        listTitleTextView.setTypeface(null, Typeface.BOLD)
//        listTitleTextView.text = listTitle
//
//        return convertedView
//    }
//
//    override fun hasStableIds(): Boolean {
//        return false
//    }
//
//    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
//        return true
//    }
