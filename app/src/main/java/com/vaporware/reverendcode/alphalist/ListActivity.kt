package com.vaporware.reverendcode.alphalist

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.vaporware.reverendcode.alphalist.R.id.aButton
import kotlin.properties.Delegates
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context


class ListActivity : AppCompatActivity() {
    var db: DbHelper by Delegates.notNull<DbHelper>()
    var arrayAdapter: CheckboxAdapter by Delegates.notNull<CheckboxAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = DbHelper(applicationContext.filesDir.absolutePath)
        db.deleteTable()
        setContentView(R.layout.activity_list)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val myList = findViewById<ListView>(R.id.mainList)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val alphaButton = findViewById<Button>(aButton)
        var ascending = true
//        TODO: this needs to be set to the itemId of the current list to be displayed
        val items = ArrayList<Equipment>()

        arrayAdapter = CheckboxAdapter(this, items, db)
        setSupportActionBar(toolbar)
        myList.adapter = arrayAdapter
        fab.setOnClickListener {
            newItem(items)
        }
        alphaButton.setOnClickListener {
            if (ascending) {
                ascending = false
                alphaButton.text = getString(R.string.ascendingText)
                items.sortBy { it.itemName }
            } else {
                ascending = true
                alphaButton.text = getString(R.string.descendingText)
                items.sortByDescending { it.itemName }
            }
            arrayAdapter.notifyDataSetChanged()
        }
        items.addAll(db.getAll())
    }

    fun newItem(items: MutableList<Equipment>) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val words = EditText(this)
        alertDialogBuilder.setPositiveButton("Save", { _, _ ->
            if (words.text.isNotBlank()) {
                val item = Equipment()
                item.itemName = words.text.toString()
                items.add(item)
                db.saveItem(item)
                arrayAdapter.notifyDataSetChanged()

            } else Toast.makeText(this,"Not adding empty task",Toast.LENGTH_SHORT).show()
        })
                .setNegativeButton("Cancel", { _, _ ->
                    words.text.clear()
                })
                .setTitle("Enter todo item")
                .setView(words)
        alertDialogBuilder.create().show()
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