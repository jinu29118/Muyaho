package com.example.thewisdomofours

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyCommentAdapter(val items:ArrayList<MyComment>):RecyclerView.Adapter<MyCommentAdapter.ViewHolder>(){
    var position: Int=0
    override fun getItemViewType(position: Int): Int {
        return position
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView1:TextView = itemView.findViewById(R.id.editor)
        val textView2:TextView = itemView.findViewById(R.id.content_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_comment, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount():Int{
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView1.text = items[position].editor
        holder.textView2.text = items[position].content
    }
}