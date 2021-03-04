package com.fhatechnology.recyclerviewitemselectionandhandling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var recyclerview:RecyclerView
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerview = findViewById(R.id.recyclerview)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(recyclerview.getContext(),
                linearLayoutManager.getOrientation())
        recyclerview.addItemDecoration(dividerItemDecoration)
        adapter.list = createRandomIntList()
        adapter.notifyDataSetChanged()

    }

    private fun createRandomIntList(): List<Int> {
        return (1..99).map { Random.nextInt() }
    }
}