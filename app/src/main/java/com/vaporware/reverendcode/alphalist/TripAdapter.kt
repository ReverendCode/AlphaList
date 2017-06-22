package com.vaporware.reverendcode.alphalist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.util.*

/**
 * Created by ReverendCode on 6/21/17.
 */

class TripAdapter(private val tripList: List<Trip>) : RecyclerView.Adapter<TripAdapter.TripViewHolder>() {


    /**
     * this holds a reference to the views for each data item
     * */
    inner class TripViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView = view.findViewById(R.id.title)
        var headerImage: ImageView = view.findViewById(R.id.headerImage)
        var dateLine = view.findViewById<TextView>(R.id.dateline)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.trip_card, parent, false)

        return TripViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val item = tripList[position]

//        holder.title.setText(movie.getTitle())
        holder.title.text = item.tripName
        holder.dateLine.text = createDateLine(item.tripDate,item.tripEndDate)
        holder.headerImage.setImageResource(item.headerImage ?: R.drawable.default_trip_image)
    }

    private fun createDateLine(start: Date?, end: Date?): String {
        return start?.toString() ?: "--"
    }

    override fun getItemCount(): Int {
        return tripList.size
    }
}