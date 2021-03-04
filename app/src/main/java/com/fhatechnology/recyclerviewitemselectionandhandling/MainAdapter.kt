package com.fhatechnology.recyclerviewitemselectionandhandling

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(var context:Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list: List<Int> = arrayListOf()
var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image_selector:ImageView = itemView.findViewById(R.id.image_selector)
         var txt_number: TextView = itemView.findViewById(R.id.txt_number)
        fun bind(value: Int, isActivated: Boolean = false) {
            txt_number.text = value.toString()
            itemView.isActivated = isActivated


        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = list[position]
        tracker?.let {
            holder.bind(number, it.isSelected(position.toLong()))
        }

        holder.itemView.setOnClickListener {
            if (!tracker!!.hasSelection()){
                Toast.makeText(context, "single clicked", Toast.LENGTH_SHORT).show()
            }
        }

        tracker?.let {
            if (it.isSelected(position.toLong()) )  {
                it.select(position.toLong())
                holder.image_selector.visibility = View.VISIBLE
                holder.txt_number.textSize = 28F
                //changing the  color of the clicked/selected item to light gray
                //parent.setBackgroundColor( ContextCompat.getColor(context, R.color.extra_light_gray))
            } else {
                it.deselect(position.toLong())
                // set color white
                holder.image_selector.visibility = View.GONE
                //parent.setBackgroundColor( ContextCompat.getColor(context, R.color.white))
            }
        }



    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long = position.toLong()
}