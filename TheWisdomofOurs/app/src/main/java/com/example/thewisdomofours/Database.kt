package com.example.thewisdomofours

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val DB_NAME = "_.db"
        const val DB_VERSION = 1
        val tableName="product"//테이블 이름
        val habitid= "habit_id"//습관 id
        val title="title"//습관 이름
        val startDate= "date"//시작날짜.
        var detail="habit_detail"//습관 설명.
        val favored="favored"//0과1로 favor했는지 확인.
        val streak="streak"//연속 완료 일 수.
        val category="category"//습관이 속한 카테고리 이름
        val completed="completed"//완료했는지
        val completeddate="completed_date"//완료한 총 일 수.
        val tableName2="catagory"//테이블 이름
        val categoryId= "category_id"//카테고리id
        val categoryname="catagory_name"//카테고리 이름.
        val tableName3="history"//히스토리 테이블 이름.
        val historyid="id"//히스토리id
        val date="date_history"

        val table_class="classname"
        val locs="locateString"
        val class_title="classTitle"
        val class_content="classContent"
        val isonline="isOnline"
        val class_cap="classCapacity"
        val class_date="classDate"
    }
    //TODO-변수들 수정. 다른데서 들고온거임.
    override fun onCreate(db: SQLiteDatabase?) {
        val create_table="create table if not exists $tableName(" +
                "$habitid integer primary key autoincrement, " +
                "$title text," +
                "$detail text," +
                "$startDate text," +
                "$streak integer," +
                "$completed integer," +
                "$favored integer," +
                "$category text," +
                "$completeddate integer);"
        val create_table2="create table if not exists $tableName2(" +
                "$categoryId integer primary key autoincrement, " +
                "$categoryname text);"
        val create_table3="create table if not exists $tableName3(" +
                "$historyid integer primary key autoincrement, " +
                "$habitid integer," +
                "$date text);"

        val create_class="create table if not exists $table_class(" +
                "$locs text," +
                "$class_title text," +
                "$class_content text," +
                "$isonline integer," +
                "$class_cap integer," +
                "$class_date text," +
        db!!.execSQL(create_table2)
        db.execSQL(create_table)
        db.execSQL(create_table3)
        db.execSQL(create_class)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    //TODO-GET,UPDATE, INSERT, DELETE예제.
    fun getStart(id:Int):String{
        val database=this.readableDatabase
        val query = "select * from $tableName where $habitid ='$id';"
        val c = database.rawQuery(query,null)
        var str = ""
        while(c.moveToNext())
            str= c.getString(c.getColumnIndex(startDate))
        database.close()
        c.close()
        return str
    }
    fun updateHabit(id:Int, name:String, detail2:String):Boolean{
        val strsql = "select * from $tableName where $habitid='$id';"
        val db = writableDatabase
        val cursor = db.rawQuery(strsql, null)
        val flag = cursor.moveToFirst()
        if(flag) {
            val values = ContentValues()
            values.put(title, name)
            values.put(detail, detail2)
            db.update(
                    tableName, values, "$title=? AND $detail=?", arrayOf(
                    cursor.getString(cursor.getColumnIndex(title)),
                    cursor.getString(cursor.getColumnIndex(detail))
            )
            )
        }
        cursor.close()
        db.close()
        return flag
    }
    /*fun insertHabitHist(habitHistory: HabitHistory){
        val values = ContentValues()
        values.putNull(historyid)
        values.put(habitid,habitHistory.habitId)
        values.put(date, habitHistory.date)
        val db = writableDatabase
        db.insert(tableName3,null,values)
    }*/
    fun deleteHistory(id2:Int, date2:String) {
        val db = this.writableDatabase
        db.delete(tableName3, "$habitid=? AND $date=?", arrayOf(id2.toString(), date2))
        db.close()
    }
    //TODO- DB작성.
}