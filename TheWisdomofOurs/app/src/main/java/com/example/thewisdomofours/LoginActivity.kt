package com.example.thewisdomofours

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
    }
    private fun init(){
        //TODO 로그인 되있으면 넘어가기.-판정을 위해 내부 db필요. 로그인을 위해 firebase필요.
    }
}