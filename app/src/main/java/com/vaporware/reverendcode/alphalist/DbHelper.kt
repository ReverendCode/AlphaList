package com.vaporware.reverendcode.alphalist

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by ReverendCode on 6/7/17.
 */

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context, "example.db", null, 4) {
    val TAG = javaClass.simpleName
    val TABLE = "logs"

    companion object {
        val ID: String = "_id"
        val TIMESTAMP: String = "TIMESTAMP"
        val TEXT: String = "TEXT"
        val LIST: String = "LIST"
        val CHECKED: String = "CHECKED"
    }

    val DATABASE_CREATE =
            "CREATE TABLE if not exists " + TABLE + " (" +
                    "$ID integer PRIMARY KEY autoincrement," +
                    "$TIMESTAMP integer," +
                    "$TEXT text" +
                    "$LIST text" +
                    "$CHECKED integer" +
                    ")"

    fun addItemToDB(item: CheckItem) {

        val values = ContentValues()
        values.put(TEXT, item.text)
        values.put(LIST, item.list)
        values.put(CHECKED, item.checked)
        values.put(TIMESTAMP, item.timestamp)

        writableDatabase.insert(TABLE, null, values)
        writableDatabase.close()

    }

    fun getCheckItemsInList(listID: String): List<CheckItem> {
        val retVal = mutableListOf<CheckItem>()
        val cursor = readableDatabase
                .query(TABLE,
                        arrayOf(ID, TIMESTAMP, TEXT, LIST, CHECKED),
                        LIST,
                        arrayOf(listID),
                        null,
                        null,
                        null)
        if (cursor.moveToFirst()) {
            do {
                retVal.add(CheckItem(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3) == 1,
                        cursor.getString(4)))
            } while (cursor.moveToNext())
        }
        return retVal
    }

    fun getListCursor(list: String): Cursor {
        return readableDatabase
                .query(TABLE, arrayOf(ID, TIMESTAMP, TEXT, LIST, CHECKED), LIST, arrayOf(list), null, null, null)
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Creating: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
    }

}

