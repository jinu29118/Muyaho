package com.example.thewisdomofours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.ActivityHotLectureBinding

class HotLectureActivity : AppCompatActivity() {
    lateinit var binding: ActivityHotLectureBinding
    lateinit var adapter: MyRecyclerAdapter
    lateinit var db:Database

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
        adapter.itemClickListener = object :MyRecyclerAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyRecyclerAdapter.ViewHolder,
                view: View,
                data: MySubLectureData,
                position: Int
            ) {
                val intent = Intent(this@HotLectureActivity, DetailedLectureActivity::class.java)
                intent.putExtra("id", data.class_id)
                startActivity(intent)
            }
        }
        binding!!.recyclerView.adapter = adapter
    }

    private fun initData() {
        db = Database(this)
        hotLecArr.add(MySubLectureData(-1, "Make Kimchi", "Let's learn how to make korea traditional food", "Food"))
        for (i in 2..6){
            val tmp = "category" + i.toString()
            hotLecArr.add(MySubLectureData(i, db.getTitle(i), db.getDetail(i), tmp))
        }
        //hotLecArr.add(MySubLectureData(1, "Lecture1", "lecture111", "#category1"))
        //hotLecArr.add(MySubLectureData(2, "Lecture2", "lecture222", "#category2"))
        //hotLecArr.add(MySubLectureData(3, "Lecture3", "lecture333", "#category3"))
        //hotLecArr.add(MySubLectureData(4, "Lecture4", "lecture444", "#category4"))
        //hotLecArr.add(MySubLectureData(5, "Lecture5", "lecture555", "#category5"))
        //hotLecArr.add(MySubLectureData(6, "Lecture6", "lecture666", "#category6"))
    }
}