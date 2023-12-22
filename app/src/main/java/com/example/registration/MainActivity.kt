package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.registration.database.DBManager

class MainActivity : AppCompatActivity() {
    val myDBManager = DBManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        myDBManager.openDB()
        super.onResume()
        val listView = findViewById<ListView>(R.id.ShoppingList)
        val list : MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
        val dataList = myDBManager.readDBData()
        for (item in dataList){
            adapter.insert(item, 0)
        }
    }

    fun onClickSave(view: View){
        val listView = findViewById<ListView>(R.id.ShoppingList)
        val data : EditText = findViewById(R.id.Element)
        val list : MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
        if (data.text.toString() != ""){
            myDBManager.insertToDB(data.text.toString(), 1)}
        val dataList = myDBManager.readDBData()
        for (item in dataList){
            adapter.insert(item, 0)
            data.setText("")
        }
    }

    fun onClickClean(view: View){
        myDBManager.recreateDB()
        val listView = findViewById<ListView>(R.id.ShoppingList)
        val list : MutableList<String> = mutableListOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }
}