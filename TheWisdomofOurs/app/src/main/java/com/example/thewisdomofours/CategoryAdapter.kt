package com.example.thewisdomofours

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CategoryAdapter(val categoryList: ArrayList<Category>, val context: Context) : RecyclerView.Adapter<CategoryAdapter.CustomViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CustomViewHolder(view).apply{
            btnmore.setOnClickListener( View.OnClickListener {
                val intent = Intent(context,MakeFoodActivity::class.java)
                context.startActivity(intent)
            })
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CustomViewHolder, position: Int) {
        holder.categoryName.text = categoryList.get(position).categoryName
        holder.bestLecture.text = categoryList.get(position).bestLecture
        holder.more = categoryList.get(position).more
    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName = itemView.findViewById<TextView>(R.id.txt_categoryName)
        val bestLecture = itemView.findViewById<TextView>(R.id.txt_bestLecture)
        val btnmore = itemView.findViewById<FloatingActionButton>(R.id.btn_more)
        var more: String = ""
    }
}