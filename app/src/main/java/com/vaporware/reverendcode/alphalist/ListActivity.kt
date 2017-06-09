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
        setContentView(R.layout.activity_list)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val myList = findViewById<ListView>(R.id.mainList)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val alphaButton = findViewById<Button>(aButton)
        var ascending = true
//        TODO: this needs to be set to the id of the current list to be displayed
        val items = ArrayList<ItemModel>()

        arrayAdapter = CheckboxAdapter(this, items, db)
        setSupportActionBar(toolbar)
        myList.adapter = arrayAdapter
        fab.setOnClickListener {
            newItem(items)
        }
        alphaButton.setOnClickListener {
            if (ascending) {
                ascending = false
                items.sortBy { it.text }
            } else {
                ascending = true
                items.sortByDescending { it.text }
            }
            arrayAdapter.notifyDataSetChanged()
        }
        items.addAll(db.retrieveItemList(0))
    }

    fun newItem(items: MutableList<ItemModel>) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val words = EditText(this)
        alertDialogBuilder.setPositiveButton("Save", { _, _ ->
            if (words.text.isNotBlank()) {
                val item = ItemModel()
                item.text = words.text.toString()
                item.listID = 0
                item.timestamp = System.currentTimeMillis()
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
        val dialog = alertDialogBuilder.create()
        dialog.show()
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

    fun removeItem(view: View) {
        Toast.makeText(this,"remove: ", Toast.LENGTH_SHORT).show()
    }

}

class dbUpdateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"words",Toast.LENGTH_SHORT).show()

    }
}