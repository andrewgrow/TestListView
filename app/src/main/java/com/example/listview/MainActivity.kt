package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.ListView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var myAdapter: MyAwesomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        makeAdapter()
    }

    private fun makeAdapter() {
        myAdapter = MyAwesomeAdapter(arrayOf("one","two","three","four","five","six","seven","eight","nine","ten"), this)
        listView.adapter = myAdapter
    }

    override fun onResume() {
        super.onResume()
        val showPosition = Random.nextInt(9)
        myAdapter.setPositionToShow(showPosition)
        myAdapter.notifyDataSetChanged()
    }
}