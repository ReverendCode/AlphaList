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
import android.support.v7.widget.RecyclerView


class ListActivity : AppCompatActivity() {

    var recyclerView: RecyclerView by Delegates.notNull<RecyclerView>()
    var equipmentList: MutableList<Equipment> = mutableListOf()
    var equipmentAdapter: EquipmentAdapter by Delegates.notNull<EquipmentAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView = findViewById(R.id.mainList)
        equipmentAdapter = EquipmentAdapter(equipmentList)

    }

    fun addItems(): List<Equipment> {
        val tent = Equipment()
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

}

class dbUpdateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"Caught a DB broadcast!",Toast.LENGTH_SHORT).show()

    }
}