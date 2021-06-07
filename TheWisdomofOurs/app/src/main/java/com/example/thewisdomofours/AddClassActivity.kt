package com.example.thewisdomofours

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class AddClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)
        init()

    }
    private fun init(){
        val cancelBtn = findViewById<Button>(R.id.cancelBtnMake)
        val createBtn = findViewById<Button>(R.id.createBtnMake)
        var title=findViewById<EditText>(R.id.title_make)
        var content=findViewById<EditText>(R.id.content_make)
        var address=findViewById<EditText>(R.id.address_make)
        var date=findViewById<EditText>(R.id.date_make)
        var online = true
        val picker = findViewById<NumberPicker>(R.id.capacitypicker)
        picker.minValue=1
        picker.maxValue=20
        RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when(group?.id){
                R.id.rg1->
                    when(checkedId){
                        R.id.radioButton_detail->
                            online=true
                        R.id.radioButton_detail2->
                            online=false
                    }
            }
        }
        cancelBtn.setOnClickListener{
            onBackPressed()
        }
        createBtn.setOnClickListener{
            //TODO 나중에 이부분 구현.
            //var capacity = picker.value
            onBackPressed()
        }

    }
}