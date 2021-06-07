package com.example.thewisdomofours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.ActivityHotLectureBinding

class HotLectureActivity : AppCompatActivity() {
    lateinit var binding: ActivityHotLectureBinding
    lateinit var adapter: MyRecyclerAdapter

    var tmpArr = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initRecycler()
    }

    private fun initRecycler() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyRecyclerAdapter(tmpArr)
        binding!!.recyclerView.addItemDecoration(DividerItemDecoration(this, 1))
        binding!!.recyclerView.adapter = adapter
    }

    private fun initData() {
        tmpArr.add("Title1")
        tmpArr.add("Title2")
        tmpArr.add("Title3")
        tmpArr.add("Title4")
        tmpArr.add("Title5")
    }
}