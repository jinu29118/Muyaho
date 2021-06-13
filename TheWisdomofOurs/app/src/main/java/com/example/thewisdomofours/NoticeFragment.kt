package com.example.thewisdomofours

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.FragmentNoticeBinding


class NoticeFragment : Fragment() {
    var binding: FragmentNoticeBinding?=null
    lateinit var adapter: MyNoticeAdapter

    var notices = arrayListOf<MyNoticeData>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentNoticeBinding.inflate(layoutInflater)
        initData()
        return binding!!.root
    }

    private fun initData() {
        notices.add(MyNoticeData("Notice3", "2021.06.13", false))
        notices.add(MyNoticeData("Notice2", "2021.06.12", false))
        notices.add(MyNoticeData("Notice1", "2021.06.11", false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.noticeView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = MyNoticeAdapter(notices)
        binding!!.noticeView.addItemDecoration(DividerItemDecoration(context, 1))
        adapter.itemClickListener = object :MyNoticeAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyNoticeAdapter.ViewHolder,
                view: View,
                data: MyNoticeData,
                position: Int
            ) {
                if(!data.isClicked){
                    holder.binding.noticeBackground.setBackgroundColor(Color.LTGRAY)
                    data.isClicked = true
                }
            }

        }
        binding!!.noticeView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}