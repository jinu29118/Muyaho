package com.example.thewisdomofours

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityAgeBinding

class AgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityAgeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, RecommandActivity::class.java)

        binding.button2.setOnClickListener{
            startActivity(intent)
        }
    }
}