package com.vaporware.reverendcode.alphalist

import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.vaporware.reverendcode.alphalist.R.id.aButton

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        val myList = findViewById<ListView>(R.id.mainList)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val alphaButton = findViewById<Button>(aButton)
        var ascending = true
        var items = mutableListOf<CheckItem>()
        val arrayAdapter = CheckboxAdapter(this, items as ArrayList<CheckItem>)

//        items should be filled with any items that exist in the current list


        setSupportActionBar(toolbar)
        myList.adapter = arrayAdapter
        fab.setOnClickListener {
            newItem(items)
            arrayAdapter.notifyDataSetChanged()

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

    }

    fun newItem(items: MutableList<CheckItem>) {
//        if the last item has text, create a new last item
        val alertDialogBuilder = AlertDialog.Builder(this)
        val words = EditText(this)
        alertDialogBuilder.setPositiveButton("Save", { _, _ ->
//            do saving things here
            if (words.text.isNotBlank()) {
                items.add(CheckItem(text = words.text.toString()))
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
