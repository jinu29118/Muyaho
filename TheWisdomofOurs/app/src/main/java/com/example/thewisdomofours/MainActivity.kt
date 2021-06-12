package com.example.thewisdomofours

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val iconarr = arrayListOf<Int>(R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_format_list_bulleted_24, R.drawable.ic_baseline_notifications_24, R.drawable.ic_baseline_person_24)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    private fun init() {
        binding.viewPager.adapter = MyFragStateAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
                tab, position ->
            tab.setIcon(iconarr[position])
        }.attach()
    }
}