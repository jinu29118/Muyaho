package com.example.thewisdomofours

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MakeFoodAdapter(val makeFoodList: ArrayList<MakeFood>, val context: Context) : RecyclerView.Adapter<MakeFoodAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MakeFoodAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply{
            btninformation.setOnClickListener( View.OnClickListener {
                val intent = Intent(context,HowtomakeKimchi::class.java)
                context.startActivity(intent)
            })
        }
    }

    override fun getItemCount(): Int {
        return makeFoodList.size
    }

    override fun onBindViewHolder(holder: MakeFoodAdapter.CustomViewHolder, position: Int) {
        holder.lectureName?.text = makeFoodList.get(position).lectureName
        holder.explain?.text = makeFoodList.get(position).explain
        holder.capacity?.text= makeFoodList.get(position).capacity
        holder.nextpage = makeFoodList.get(position).nextpage
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lectureName = itemView.findViewById<TextView>(R.id.txt_lectureName)
        val explain = itemView.findViewById<TextView>(R.id.txt_explain)
        val capacity = itemView.findViewById<TextView>(R.id.txt_capacity)
        val btninformation = itemView.findViewById<FloatingActionButton>(R.id.btn_more)
        var nextpage: String = ""
    }
}