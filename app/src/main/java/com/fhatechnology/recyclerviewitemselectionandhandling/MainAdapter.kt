package com.fhatechnology.recyclerviewitemselectionandhandling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list: List<Int> = arrayListOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var txt_number: TextView = itemView.findViewById(R.id.txt_number)
        fun bind(number: Int) {
            txt_number.setText("${number}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = list[position]
        holder.bind(number)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}