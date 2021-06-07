package com.example.thewisdomofours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MakeFoodActivity : AppCompatActivity() {
    private var rv_makefood : RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_food)
        val makeFoodList = arrayListOf(
            MakeFood("How to make Kimchi", "Let's learn how to make kimchi", " - 7/8", "HowtomakeKimchi"),
            MakeFood("Cooking Class", "Basics of Korean food", "- 8/8", "HowtomakeKimchi"),
            MakeFood("Ramen doctor", "World best Ramen", "- 5/8", "HowtomakeKimchi"),
            MakeFood("The price of Japenece food", "Sushi making", "- 4/4", "HowtomakeKimchi"),
            MakeFood("Steak artisan", "Basics of fire control", "- 3/4", "HowtomakeKimchi")

        )


        rv_makefood = findViewById(R.id.rv_makefood)
        rv_makefood?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        rv_makefood?.setHasFixedSize(true)
        rv_makefood?.adapter = MakeFoodAdapter(makeFoodList,this)

    }
}