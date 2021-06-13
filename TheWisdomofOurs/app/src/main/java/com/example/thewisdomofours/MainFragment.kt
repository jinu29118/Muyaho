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
    //lateinit var pref: SharedPreferences
    //lateinit var editor: SharedPreferences.Editor

    var tmpArr = arrayListOf<String>("Hot Lecture1", "Hot Lecture2", "Hot Lecture3")
    var tmpArr2 = arrayListOf<String>("My Lecture1", "My Lecture2", "My Lecture3")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        initRecycler()
        return binding!!.root
    }

    private fun initRecycler() {
        binding!!.hotLectureView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter1 = MyRecyclerAdapter(tmpArr)
        binding!!.hotLectureView.addItemDecoration(DividerItemDecoration(context, 1))

        binding!!.myLectureView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter2 = MyRecyclerAdapter(tmpArr2)
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