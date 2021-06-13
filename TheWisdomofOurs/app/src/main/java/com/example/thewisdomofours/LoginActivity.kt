package com.example.thewisdomofours

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityLoginBinding
import com.example.thewisdomofours.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent= Intent(this, LocationActivity::class.java)
        val intent2= Intent(this, ChangingPW::class.java)

        binding.login2.setOnClickListener{
            startActivity(intent)
        }

        binding.forgetPw.setOnClickListener{
            startActivity(intent2)
        }
    }
}