package com.vaporware.reverendcode.alphalist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox


class CheckboxAdapter(mContext: Context, var mDataSource: ArrayList<ItemModel>, val db: DbHelper) : BaseAdapter() {
       var mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getItem(p0: Int): Any {
        return mDataSource[p0]
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
        val item = getItem(position) as ItemModel
        val button = rowView.findViewById<Button>(R.id.cancel)
        button.setOnClickListener {
            db.deleteItem(item)
            mDataSource.remove(item)
            this.notifyDataSetChanged()
        }
        checkBox.setOnClickListener {
            item.checked = checkBox.isChecked
            db.updateItem(item)
        }
        checkBox.text = item.itemName ?: ""
        checkBox.isChecked = item.checked
        return rowView
    }
}