package com.example.thewisdomofours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryActivity : AppCompatActivity() {

    private var rv_category : RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryList = arrayListOf(
            Category("#MakeFood", "how to make kimchi", "MakeFoodActivity"),
            Category("#Gradening", "a small tip in gardening", "MakeFoodActivity"),
            Category("#Traditional Game", "learn about Hwato", "MakeFoodActivity"),
            Category("#Music", "Vocal Lecture", "MakeFoodActivity"),
            Category("#Coding", "Pyton Lecture", "MakeFoodActivity"),
            Category("#Painting", "Painting Class", "MakeFoodActivity")
        )


        rv_category = findViewById(R.id.rv_category)
        rv_category?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        rv_category?.setHasFixedSize(true)
        rv_category?.adapter = CategoryAdapter(categoryList,this)

    }
}