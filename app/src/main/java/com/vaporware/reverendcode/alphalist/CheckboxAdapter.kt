package com.vaporware.reverendcode.alphalist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import android.text.Editable
import android.text.TextWatcher


class CheckboxAdapter(mContext: Context, var mDataSource: ArrayList<CheckItem>) : BaseAdapter() {
       var mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getItem(p0: Int): Any {
        return mDataSource.get(p0)
    }

    override fun getItemId(p0: Int): Long {

        return p0.toLong()
    }

    override fun getCount(): Int {
        return mDataSource.count()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val rowView = mInflater.inflate(R.layout.list_item, parent, false)
        val checkBox: CheckBox = rowView.findViewById(R.id.check)
        val item = getItem(position) as CheckItem


        checkBox.text = item.text ?: ""
        checkBox.isChecked = item.checked
        return rowView
    }
}



data class CheckItem(var checked: Boolean = false, var text: String? = null)
