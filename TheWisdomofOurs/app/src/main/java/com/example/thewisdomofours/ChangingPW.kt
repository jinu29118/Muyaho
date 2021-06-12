package com.example.thewisdomofours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thewisdomofours.databinding.ActivityChangingPwBinding
import com.example.thewisdomofours.databinding.ActivityRegisterBinding

class ChangingPW : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityChangingPwBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent= Intent(this, LoginActivity::class.java)
        binding.cancel2.setOnClickListener{
            startActivity(intent)
        }
        binding.submit2.setOnClickListener{
            startActivity(intent)
        }
    }
}