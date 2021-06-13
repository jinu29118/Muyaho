package com.example.thewisdomofours

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailedLectureActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    var db = Database(this)
    var id = -1
    var data = ArrayList<MyComment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_class)
        if (intent.hasExtra("id")){
            id = intent.getIntExtra("id",-1)
        }else{
            id=-1
        }
        init()
    }
    private fun init() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView_detailed)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val title = findViewById<TextView>(R.id.title_detail)
        val detail = findViewById<TextView>(R.id.detail_detail)
        val location = findViewById<TextView>(R.id.location)
        val content = findViewById<EditText>(R.id.content_comment)
        val capacity = findViewById<TextView>(R.id.capacity_detail)
        val tuto = findViewById<ImageView>(R.id.imageTuto2)
        val skip = findViewById<TextView>(R.id.detail_text3)
        tuto.setOnClickListener {
            tuto.visibility = View.GONE
            skip.visibility = View.GONE
        }
        skip.setOnClickListener {
            tuto.visibility = View.GONE
            skip.visibility = View.GONE
        }
        initData()
        val adapter = MyCommentAdapter(data)
        recyclerView.adapter = adapter
        if(id==0){
            title.setText("How to make kimchi")
            detail.setText("Let's learn how to make kimchi and make together!")
            location.setText("40, Saecheonnyeon-ro, 407-1103, at 21.6.2021, 6:00pm")
            capacity.setText("7/8")
        }else {
            initData()
            val applybtn = findViewById<Button>(R.id.applyBtn)
            val sendbtn = findViewById<Button>(R.id.button5)
            title.setText(db.getTitle(id))
            detail.setText(db.getDetail(id))
            location.setText(db.getLocation(id))
            val cap = db.getPeople(id).toString()+"/"+db.getCapacity(id).toString()
            capacity.setText(cap)
            applybtn.setOnClickListener {
                if(db.getAdded(id)==0){
                    if(db.getCapacity(id)>db.getPeople(id)){
                        db.addPeople(id)
                        db.setAdded(id, 1)
                        Toast.makeText(this,"you have successfully applied class", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this,"Sorry, but capacity is full", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    db.minusPeople(id)
                    db.setAdded(id, 0)
                    Toast.makeText(this,"you have successfully withdrawn this class", Toast.LENGTH_SHORT).show()
                }
                init()
            }
            sendbtn.setOnClickListener {
                //TODO-편집자 이름 추가.
                if(id==-1){
                    data.add(MyComment("user",content.text.toString()))
                    val adapter = MyCommentAdapter(data)
                    recyclerView.adapter = adapter
                }else {
                    val editor = "User"
                    db.insertComment(editor, content.text.toString(), id)
                    init()
                }
            }
        }
    }
    private fun initData(){

        data.clear()
        if(id==-1)
            data.add(MyComment("user1 name","How long is the lecture?"))
        else
            data=db.getComment(id)
    }
}