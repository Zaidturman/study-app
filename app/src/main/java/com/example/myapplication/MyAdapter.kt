package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter( private val takeList: ArrayList<Task>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item,
        parent, false)
        return  MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = takeList[position]

        holder.type.text = currentitem.type
        holder.title.text = currentitem.title
        holder.date.text = currentitem.etdate
        holder.time.text = currentitem.ettime

    }

    override fun getItemCount(): Int {
        return takeList.size
    }




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val type : TextView = itemView.findViewById(R.id.tvtype)
        val title : TextView = itemView.findViewById(R.id.tvtitle)
        val date : TextView = itemView.findViewById(R.id.tvdate)
        val time : TextView = itemView.findViewById(R.id.tvTime)


    }
}