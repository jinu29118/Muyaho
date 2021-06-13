package com.example.thewisdomofours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.ActivityHotLectureBinding

class HotLectureActivity : AppCompatActivity() {
    lateinit var binding: ActivityHotLectureBinding
    lateinit var adapter: MyRecyclerAdapter

    var hotLecArr = arrayListOf<MySubLectureData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initRecycler()
    }

    private fun initRecycler() {
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyRecyclerAdapter(hotLecArr)
        binding!!.recyclerView.addItemDecoration(DividerItemDecoration(this, 1))
        binding!!.recyclerView.adapter = adapter
    }

    private fun initData() {
        hotLecArr.add(MySubLectureData(1, "Lecture1", "lecture111", "#category1"))
        hotLecArr.add(MySubLectureData(2, "Lecture2", "lecture222", "#category2"))
        hotLecArr.add(MySubLectureData(3, "Lecture3", "lecture333", "#category3"))
        hotLecArr.add(MySubLectureData(4, "Lecture4", "lecture444", "#category4"))
        hotLecArr.add(MySubLectureData(5, "Lecture5", "lecture555", "#category5"))
        hotLecArr.add(MySubLectureData(6, "Lecture6", "lecture666", "#category6"))
    }
}