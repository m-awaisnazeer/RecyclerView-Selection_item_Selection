package com.fhatechnology.recyclerviewitemselectionandhandling

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var recyclerview:RecyclerView
    private val adapter = MainAdapter(this)
    lateinit var txt_selected:TextView


    var tracker: SelectionTracker<Long>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txt_selected = findViewById(R.id.txt_selected)

        recyclerview = findViewById(R.id.recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerview.getContext(),
                linearLayoutManager.getOrientation())
        recyclerview.addItemDecoration(dividerItemDecoration)
        adapter.list = createRandomIntList()
        adapter.notifyDataSetChanged()
        tracker = SelectionTracker.Builder<Long>(
                "mySelection",
                recyclerview,
                MyItemKeyProvider(recyclerview),
                MyItemDetailsLookup(recyclerview),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
                SelectionPredicates.createSelectAnything()
        ).build()
        adapter.tracker = tracker

        tracker?.addObserver(
                object : SelectionTracker.SelectionObserver<Long>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        val items = tracker?.selection!!
                        if (items.size()>0){
                            findViewById<RelativeLayout>(R.id.selected_txt_layout).visibility = View.VISIBLE
                            for ((index,value) in items.withIndex()){
                                txt_selected.append("${value.toString()}\n")
                            }
                            txt_selected.append("       #################\n")

                        }
                    }
                })
    }

    private fun createRandomIntList(): List<Int> {
        return (1..99).map { it*2 }
    }
}