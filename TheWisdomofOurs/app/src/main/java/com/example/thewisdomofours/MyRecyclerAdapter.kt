package com.example.thewisdomofours

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thewisdomofours.databinding.RowBinding

class MyRecyclerAdapter(val items:ArrayList<MySubLectureData>) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder: MyRecyclerAdapter.ViewHolder, view: View, data: MySubLectureData, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.lectureView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyRecyclerAdapter.ViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyRecyclerAdapter.ViewHolder, position: Int) {
        holder.binding.titleView.text = items[position].class_title
        holder.binding.contentView.text = items[position].class_content
        holder.binding.categoryView.text = "#" + items[position].class_category
    }

    override fun getItemCount(): Int {
        return items.size
    }
}