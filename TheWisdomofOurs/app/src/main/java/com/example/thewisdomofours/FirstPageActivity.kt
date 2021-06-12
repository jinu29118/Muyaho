package com.example.thewisdomofours

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thewisdomofours.databinding.ActivityFirstPageBinding

import com.example.thewisdomofours.databinding.ActivityLoginBinding

class FirstPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val binding= ActivityFirstPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent= Intent(this, LoginActivity::class.java)
        val intent2= Intent(this, RegisterActivity::class.java)
        binding.loginStart.setOnClickListener{
            startActivity(intent)
        }
        binding.Register.setOnClickListener{
            startActivity(intent2)
        }
    }
}