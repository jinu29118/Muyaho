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

    var tmpArr = arrayListOf<String>("Cooking Class", "Python Class", "Web Design")
    var tmpArr2 = arrayListOf<String>("My Lecture1", "My Lecture2", "My Lecture3")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyLectureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initRecycler()
    }

    private fun init(){
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
        adapter1 = MyRecyclerAdapter(tmpArr)
        binding.lectureCreate.addItemDecoration(DividerItemDecoration(this, 1))

        binding.lectureEnter.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter2 = MyRecyclerAdapter(tmpArr2)
        binding.lectureEnter.addItemDecoration(DividerItemDecoration(this, 1))

        binding.lectureEnter.adapter = adapter2
        binding.lectureCreate.adapter = adapter1
    }
}