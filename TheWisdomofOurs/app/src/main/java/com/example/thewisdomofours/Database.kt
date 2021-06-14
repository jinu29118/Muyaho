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
        val class_id="classId"
        val class_people="now_people"
        val class_added="class_added"

        val table_comment="commentable"
        val editor="editor"
        val comment_content="comment_content"
        val comment_id="comment_id"
        val comment_id2="real_id"
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
                "$class_people int," +
                "$class_added int," +
                "$class_id integer primary key autoincrement);"
        val create_comment="create table if not exists $table_comment(" +
                "$comment_id integer," +
                "$editor text," +
                "$comment_id2 integer primary key autoincrement," +
                "$comment_content text);"
        db!!.execSQL(create_table2)
        db.execSQL(create_table)
        db.execSQL(create_table3)
        db.execSQL(create_class)
        db.execSQL(create_comment)
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
    fun insertClass(loc:String, title:String, content:String, online:Int, cap:Int, date:String){
        val values = ContentValues()
        values.put(locs, loc)
        values.put(class_title, title)
        values.put(class_content, content)
        values.put(isonline, online)
        values.put(class_cap, cap)
        values.put(class_date, date)
        values.put(class_people, 0)
        values.put(class_added, 0)
        values.putNull(class_id)
        val db = writableDatabase
        db.insert(table_class,null,values)
    }
    fun getTitle(id:Int):String{
        val database=this.readableDatabase
        val query = "select * from $table_class where $class_id ='$id';"
        val c = database.rawQuery(query,null)
        var str = ""
        while(c.moveToNext())
            str= c.getString(c.getColumnIndex(class_title))
        database.close()
        c.close()
        return str
    }
    fun getLocation(id:Int):String{
        val database=this.readableDatabase
        val query = "select * from $table_class where $class_id ='$id';"
        val c = database.rawQuery(query,null)
        var str = ""
        while(c.moveToNext()) {
            val str1 = c.getString(c.getColumnIndex(class_date))
            val str2 = c.getString(c.getColumnIndex(locs))
            str = "$str2, $str1"
        }
        database.close()
        c.close()
        return str
    }
    fun getDetail(id:Int):String{
        val database=this.readableDatabase
        val query = "select * from $table_class where $class_id ='$id';"
        val c = database.rawQuery(query,null)
        var str = ""
        while(c.moveToNext())
            str= c.getString(c.getColumnIndex(class_content))
        database.close()
        c.close()
        return str
    }
    fun getPeople(id:Int):Int{
        val database=this.readableDatabase
        val query = "select * from $table_class where $class_id ='$id';"
        val c = database.rawQuery(query,null)
        var str = 1
        while(c.moveToNext())
            str= c.getInt(c.getColumnIndex(class_people))
        database.close()
        c.close()
        return str
    }
    fun getCapacity(id:Int):Int{
        val database=this.readableDatabase
        val query = "select * from $table_class where $class_id ='$id';"
        val c = database.rawQuery(query,null)
        var str = 0
        while(c.moveToNext())
            str= c.getInt(c.getColumnIndex(class_cap))
        database.close()
        c.close()
        return str
    }
    fun getAdded(id:Int):Int{
        val database=this.readableDatabase
        val query = "select * from $table_class where $class_id ='$id';"
        val c = database.rawQuery(query,null)
        var str = 0
        while(c.moveToNext())
            str= c.getInt(c.getColumnIndex(class_added))
        database.close()
        c.close()
        return str
    }
    fun addPeople(id: Int) {
        val strsql = "select * from $table_class where $class_id='$id';"
        val db = writableDatabase
        val db2 = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val cursor2 = db2.rawQuery(strsql, null)
        val values = ContentValues()
        while(cursor2.moveToNext()){
            val str = cursor2.getInt(cursor.getColumnIndex(class_people))
            values.put(class_people, (str + 1))
            println(str + 1)
        }
        var flag = cursor.moveToFirst()
        if(flag) {
            db.update(
                table_class, values, "$class_people=?",
                arrayOf(
                    cursor.getInt(cursor.getColumnIndex(class_people)).toString()
                )
            )
            println("+")
        }
        cursor2.close()
        db.close()
    }
    fun minusPeople(id: Int) {
        val strsql = "select * from $table_class where $class_id='$id';"
        val db = writableDatabase
        val db2 = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val cursor2 = db2.rawQuery(strsql, null)
        val values = ContentValues()
        while(cursor2.moveToNext()){
            val str = cursor2.getInt(cursor.getColumnIndex(class_people))
            values.put(class_people, (str - 1))
            println(str - 1)
        }
        var flag = cursor.moveToFirst()
        if(flag) {
            db.update(
                table_class, values, "$class_people=?",
                arrayOf(
                    cursor.getInt(cursor.getColumnIndex(class_people)).toString()
                )
            )
            println("+")
        }
        cursor2.close()
        db.close()
    }
    fun setAdded(id:Int, added:Int):Boolean{
        val strsql = "select * from $table_class where $class_id='$id';"
        val db = writableDatabase
        val db2 = readableDatabase
        val cursor = db.rawQuery(strsql, null)
        val cursor2 = db2.rawQuery(strsql, null)
        val values = ContentValues()
        while(cursor2.moveToNext()){
            val str = cursor2.getInt(cursor.getColumnIndex(class_added))
            values.put(class_added, added)
            println(str - 1)
        }
        var flag = cursor.moveToFirst()
        if(flag) {
            val values = ContentValues()
            values.put(class_added, added)
            db.update(
                table_class, values, "$class_added=?", arrayOf(
                    cursor.getInt(cursor.getColumnIndex(class_added)).toString()
                )
            )
        }
        cursor.close()
        db.close()
        return flag
    }
    fun getComment(id:Int):ArrayList<MyComment>{
        var AL = ArrayList<MyComment>()
        var AL2 :MyComment
        val database=readableDatabase
        val query = "select * from $table_comment where $comment_id='$id';"
        val c = database.rawQuery(query,null)
        while(c.moveToNext()){
            AL2= MyComment(c.getString(c.getColumnIndex(editor)),
                c.getString(c.getColumnIndex(comment_content)))
            AL.add(AL2)
        }
        c.close()
        database.close()
        return AL
    }
    fun insertComment(edit: String, cont:String, id:Int){
        val values = ContentValues()
        values.put(editor, edit)
        values.put(comment_content, cont)
        values.put(comment_id, id)
        values.putNull(comment_id2)
        val db = writableDatabase
        db.insert(table_comment,null,values)
    }
}