package com.example.post


import android.R.attr
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

import android.app.ListActivity
import androidx.core.content.ContextCompat.startActivity

import android.R.attr.targetActivity
import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat.startActivity





//import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter( var context: Context,  private val items: List<SItem>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pk = items[position].pk

        val name = items[position].name
        val hintt1 = items[position].taboo1
        val hintt2 = items[position].taboo2
        val hintt3 = items[position].taboo3


        holder.itemView.apply {

       nameofC.text = name
            hint1.text =  hintt1
            hint2.text =  hintt2
            hint3.text =  hintt3

            celCard.setOnClickListener {

                val intent = Intent(context, UpdateDelete::class.java)
                intent.putExtra("pk", pk)
                intent.putExtra("Cname", name)
                intent.putExtra("hint1", hintt1)
                intent.putExtra("hint2", hintt2)
                intent.putExtra("hint3", hintt3)
                celCard.context.startActivity(intent)



            }



        }
    }



    override fun getItemCount() = items.size





}

