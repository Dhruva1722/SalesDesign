package com.example.salesdesign.Adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.salesdesign.R

class CustomizedExpandableListAdapter (
    private val context: Context,
    private var expandableTitleList: List<String>,
    private var expandableDetailList: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {




    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return expandableDetailList[expandableTitleList[groupPosition]]?.get(childPosition) ?: ""
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertedView = convertView
        val expandedListText = getChild(groupPosition, childPosition) as String

        if (convertedView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertedView = inflater.inflate(R.layout.stock_details, null)
        }

        val expandedListTextView: TextView =
            convertedView?.findViewById(R.id.expandedListItem) as TextView
        expandedListTextView.text = expandedListText

        return convertedView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return expandableDetailList[expandableTitleList[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return expandableTitleList[groupPosition]
    }

    override fun getGroupCount(): Int {
        return expandableTitleList.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertedView = convertView
        val listTitle = getGroup(groupPosition) as String

        if (convertedView == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertedView = inflater.inflate(R.layout.stock_title, null)
        }

        val listTitleTextView: TextView =
            convertedView?.findViewById(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle

        return convertedView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }


    fun updateData(newTitleList: List<String>, newDetailList: HashMap<String, List<String>>) {
        expandableTitleList = newTitleList
        expandableDetailList = newDetailList
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
