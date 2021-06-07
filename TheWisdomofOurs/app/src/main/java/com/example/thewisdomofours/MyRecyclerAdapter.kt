package com.example.thewisdomofours

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thewisdomofours.databinding.RowBinding

class MyRecyclerAdapter(val items:ArrayList<String>) : RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        init {

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
        holder.binding.titleView.text = items[position]
        holder.binding.contentView.text = items[position] + " contents"
        holder.binding.categoryView.text = "#" + items[position] + " category"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}