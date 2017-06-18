package com.vaporware.reverendcode.alphalist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView
import android.view.View


class EquipmentAdapter(private val equipmentList: List<Equipment>) : RecyclerView.Adapter<EquipmentAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var title: TextView

        init {
//            title = view.findViewById(R.id.title) as TextView

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = equipmentList[position]
//        holder.title.setText(movie.getTitle())
    }

    override fun getItemCount(): Int {
        return equipmentList.size
    }
}