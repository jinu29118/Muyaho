package com.example.thewisdomofours

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.thewisdomofours.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.io.FileOutputStream

class MainActivity : AppCompatActivity(), ManageDB {
    lateinit var binding: ActivityMainBinding
    lateinit var db:Database

    val iconarr = arrayListOf<Int>(R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_format_list_bulleted_24, R.drawable.ic_baseline_notifications_24, R.drawable.ic_baseline_person_24)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDB()
        init()
    }

    private fun initDB(){
        val dbfile = getDatabasePath("_.db")
        if(!dbfile.parentFile.exists()){
            dbfile.parentFile.mkdir()
        }
        if(!dbfile.exists()){
            val file = resources.openRawResource(R.raw.klabdb)
            val fileSize = file.available()
            val buffer = ByteArray(fileSize)
            file.read(buffer)
            file.close()
            dbfile.createNewFile()
            val output = FileOutputStream(dbfile)
            output.write(buffer)
            output.close()
        }
    }

    private fun init() {
        db = Database(this)
        binding.viewPager.adapter = MyFragStateAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager){
                tab, position ->
            tab.setIcon(iconarr[position])
        }.attach()
    }

    override fun getClass(id: Int): MySubLectureData {
        return MySubLectureData(id, db.getTitle(id), db.getDetail(id), "TestCategory")
    }
}