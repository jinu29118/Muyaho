package com.example.thewisdomofours

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityRecommandBinding

class RecommandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityRecommandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent=Intent(this,MainFragment::class.java)

        binding.next.setOnClickListener{
            startActivity(intent)
        }
    }
}