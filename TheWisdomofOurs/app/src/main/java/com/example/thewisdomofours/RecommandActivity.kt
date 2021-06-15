package com.example.thewisdomofours

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.ActivityRecommandBinding

class RecommandActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecommandBinding
    lateinit var adapter: MyRecyclerAdapter

    var recommandArr = arrayListOf<MySubLectureData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecommandBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        init()
    }

    private fun init(){
        binding!!.recommandRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyRecyclerAdapter(recommandArr)
        binding!!.recommandRecycler.addItemDecoration(DividerItemDecoration(this, 1))
        adapter.itemClickListener = object :MyRecyclerAdapter.OnItemClickListener{
            override fun OnItemClick(
                    holder: MyRecyclerAdapter.ViewHolder,
                    view: View,
                    data: MySubLectureData,
                    position: Int
            ) {
                val intent = Intent(this@RecommandActivity, DetailedLectureActivity::class.java)
                intent.putExtra("id", data.class_id)
                startActivity(intent)
            }
        }
        binding!!.recommandRecycler.adapter = adapter
        binding.next.setOnClickListener{
            val intent=Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initData() {
        recommandArr.add(MySubLectureData(-1, "Make Kimchi",
                "Let's learn how to make korea traditional food", "Food"))
        recommandArr.add(MySubLectureData(2, "Vocal Lesson",
                "Learn the basic of vocalization", "Music"))
        recommandArr.add(MySubLectureData(3, "Painting Class",
                "Learn portrait painting", "Drawing"))
        recommandArr.add(MySubLectureData(4, "A small tip in gardening",
                "Why you need a pond", "Gardening"))
    }
}