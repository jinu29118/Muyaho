package com.example.thewisdomofours

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding by lazy{ActivityLocationBinding.inflate(layoutInflater)}
        setContentView(binding.root)

        val intent= Intent(this, AgeActivity::class.java)

        binding.button2.setOnClickListener {
            startActivity(intent)
        }
    }
}