package com.example.thewisdomofours

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thewisdomofours.databinding.RowNoticeBinding

class MyNoticeAdapter(val items:ArrayList<MyNoticeData>) : RecyclerView.Adapter<MyNoticeAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder: ViewHolder, view: View, data: MyNoticeData, position: Int)
    }

    var itemClickListener:OnItemClickListener?=null

    inner class ViewHolder(val binding: RowNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.noticeBackground.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, items[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyNoticeAdapter.ViewHolder {
        val view = RowNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.noticeTitle.text = items[position].content
        holder.binding.date.text = items[position].date
        if(items[position].isClicked) {
            holder.binding.noticeBackground.setBackgroundColor(Color.LTGRAY)
        }
    }
}