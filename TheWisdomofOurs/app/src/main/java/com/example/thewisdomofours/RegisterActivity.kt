package com.example.thewisdomofours

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityRegisterBinding

class RegisterActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val binding= ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent= Intent(this, LoginActivity::class.java)

        binding.cancel3.setOnClickListener{
            startActivity(intent)
        }
        binding.submit3.setOnClickListener{
            startActivity(intent)
        }
    }
}
