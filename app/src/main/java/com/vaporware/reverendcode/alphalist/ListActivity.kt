package com.vaporware.reverendcode.alphalist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import kotlin.properties.Delegates
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class ListActivity : AppCompatActivity() {

    var mRecyclerView: RecyclerView by Delegates.notNull<RecyclerView>()
    var mTripList: MutableList<Trip> = mutableListOf()
    var mAdapter: TripAdapter by Delegates.notNull<TripAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        mRecyclerView = findViewById(R.id.tripList)
        mAdapter = TripAdapter(mTripList)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
        mTripList.addAll(addTrip())
    }


    fun addTrip(): List<Trip> {
        val retVal = mutableListOf<Trip>()
        retVal.add(Trip("Bryce Canyon Car Camping",R.drawable.bryce))
        retVal.add(Trip("Default trip image test"))
        return retVal
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun itemClicked(view: View?): Unit {
//        if this gives me the behavior I want, this is the launch point for the item detail intent
        Toast.makeText(applicationContext,"An item has been touched", Toast.LENGTH_SHORT).show()
    }

    fun tripAdd(view: View?): Unit {
        Toast.makeText(applicationContext,"Add a trip",Toast.LENGTH_SHORT).show()
    }

}
