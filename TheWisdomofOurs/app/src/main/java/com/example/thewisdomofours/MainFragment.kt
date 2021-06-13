package com.example.thewisdomofours

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thewisdomofours.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    var binding: FragmentMainBinding?=null
    lateinit var adapter1: MyRecyclerAdapter
    lateinit var adapter2: MyRecyclerAdapter

    var hotLecArr = arrayListOf<MySubLectureData>()
    var myLecArr = arrayListOf<MySubLectureData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        initData()
        initRecycler()
        return binding!!.root
    }

    private fun initData() {
        hotLecArr.add(MySubLectureData(1, "Lecture1", "lecture111", "#category1"))
        hotLecArr.add(MySubLectureData(2, "Lecture2", "lecture222", "#category2"))
        hotLecArr.add(MySubLectureData(3, "Lecture3", "lecture333", "#category3"))
        myLecArr.add(MySubLectureData(1, "Lecture1", "lecture111", "#category1"))
        myLecArr.add(MySubLectureData(2, "Lecture2", "lecture222", "#category2"))
        myLecArr.add(MySubLectureData(3, "Lecture3", "lecture333", "#category3"))
    }

    private fun initRecycler() {
        binding!!.hotLectureView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter1 = MyRecyclerAdapter(hotLecArr)
        binding!!.hotLectureView.addItemDecoration(DividerItemDecoration(context, 1))
        adapter1.itemClickListener = object :MyRecyclerAdapter.OnItemClickListener{
            override fun OnItemClick(
                holder: MyRecyclerAdapter.ViewHolder,
                view: View,
                data: MySubLectureData,
                position: Int
            ) {
                val intent = Intent(activity, DetailedLectureActivity::class.java)
                startActivity(intent)
            }

        }

        binding!!.myLectureView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter2 = MyRecyclerAdapter(hotLecArr)
        binding!!.myLectureView.addItemDecoration(DividerItemDecoration(context, 1))

        binding!!.myLectureView.adapter = adapter2
        binding!!.hotLectureView.adapter = adapter1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = activity?.getSharedPreferences("tutorial", Context.MODE_PRIVATE)
        val tutoSkip = pref?.getBoolean("mainT", false)
        if(tutoSkip!!){
            binding!!.tutorial1.visibility = View.GONE
            binding!!.tutorial2.visibility = View.GONE
        }
        val editor = pref.edit()
        binding!!.apply {
            imageBtn.setOnClickListener {
                val intent = Intent(activity, HotLectureActivity::class.java)
                startActivity(intent)
            }
            imageBtn2.setOnClickListener {
                val intent = Intent(activity, MyLectureActivity::class.java)
                startActivity(intent)
            }
            tutorial1.setOnClickListener {
                tutorial2.visibility = View.VISIBLE
                tutorial1.visibility = View.GONE
            }
            tutorial2.setOnClickListener {
                tutorial2.visibility = View.GONE
                editor.putBoolean("mainT", true)
                editor.commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}