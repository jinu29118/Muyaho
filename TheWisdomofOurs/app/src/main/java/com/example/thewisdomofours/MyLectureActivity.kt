package com.example.thewisdomofours

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.ActivityMyLectureBinding

class MyLectureActivity : AppCompatActivity() {
    lateinit var binding: ActivityMyLectureBinding
    lateinit var adapter1: MyRecyclerAdapter
    lateinit var adapter2: MyRecyclerAdapter
    lateinit var db: Database

    var createLecArr = arrayListOf<MySubLectureData>()
    var enterLecArr = arrayListOf<MySubLectureData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initData()
        initRecycler()
    }

    private fun initData() {
        createLecArr.add(MySubLectureData(3, db.getTitle(3), db.getDetail(3), "Drawing"))
        createLecArr.add(MySubLectureData(4, db.getTitle(4), db.getDetail(4), "Gardening"))
        //createLecArr.add(MySubLectureData(3, "Lecture3", "lecture333", "category3"))
        enterLecArr.add(MySubLectureData(5, db.getTitle(5), db.getDetail(5), "Traditional Game"))
        enterLecArr.add(MySubLectureData(6, db.getTitle(6), db.getDetail(6), "Coding"))
        //enterLecArr.add(MySubLectureData(3, "Lecture3", "lecture333", "category3"))
    }

    private fun init(){
        db = Database(this)
        val pref = getSharedPreferences("tutorial", Context.MODE_PRIVATE)
        val tutoSkip = pref.getBoolean("lectureT", false)
        if(tutoSkip){
            binding.tutorial3.visibility = View.GONE
        }
        val editor = pref.edit()
        binding.apply {
            addBtn.setOnClickListener {
                val intent = Intent(this@MyLectureActivity, AddClassActivity::class.java)
                startActivity(intent)
            }
            tutorial3.setOnClickListener {
                tutorial3.visibility = View.GONE
                editor.putBoolean("lectureT", true)
                editor.commit()
            }
        }
    }

    private fun initRecycler() {
        binding.lectureCreate.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter1 = MyRecyclerAdapter(createLecArr)
        binding.lectureCreate.addItemDecoration(DividerItemDecoration(this, 1))
        adapter1.itemClickListener = object :MyRecyclerAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyRecyclerAdapter.ViewHolder,
                view: View,
                data: MySubLectureData,
                position: Int
            ) {
                val intent = Intent(this@MyLectureActivity, DetailedLectureActivity::class.java)
                intent.putExtra("id", data.class_id)
                startActivity(intent)
            }
        }

        binding.lectureEnter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter2 = MyRecyclerAdapter(enterLecArr)
        binding.lectureEnter.addItemDecoration(DividerItemDecoration(this, 1))
        adapter2.itemClickListener = object :MyRecyclerAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyRecyclerAdapter.ViewHolder,
                view: View,
                data: MySubLectureData,
                position: Int
            ) {
                val intent = Intent(this@MyLectureActivity, DetailedLectureActivity::class.java)
                intent.putExtra("id", data.class_id)
                startActivity(intent)
            }
        }

        binding.lectureEnter.adapter = adapter2
        binding.lectureCreate.adapter = adapter1
    }
}