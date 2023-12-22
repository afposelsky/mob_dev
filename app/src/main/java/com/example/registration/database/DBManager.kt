package com.example.registration.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.icu.text.CaseMap.Title

class DBManager(val context: Context) {
    val dbHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = dbHelper.writableDatabase
    }

    fun insertToDB(title: String, content: Int){
        val values = ContentValues().apply {
            put(DBNameClass.ELEMENT_NAME, title)
            put(DBNameClass.COUNT, content)
        }
        db?.insert(DBNameClass.TABLE_NAME, null, values)
    }

    fun readDBData(): ArrayList<String>{
        val dataList = ArrayList<String>()
        var cursor = db?.query(
            DBNameClass.TABLE_NAME, null, null, null,
            null, null, null)
        with(cursor){
            while (this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndexOrThrow(DBNameClass.ELEMENT_NAME))
                dataList.add(dataText.toString())
            }
            cursor?.close()
        }
        return dataList
    }

    fun recreateDB(){
        db?.execSQL("delete from "+ DBNameClass.TABLE_NAME)
    }

    fun closeDB(){
        db?.close()
    }


}