package br.com.pdm.bookbycode.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by raimundo on 25/02/18.
 */

class DatabaseManager(context: Context?) : SQLiteOpenHelper(context, "book.sql3", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "create table book(id integer primary key autoincrement, title text, url_book text);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table book")
        this.onCreate(db)
    }



//    fun onRemove( db: SQLiteDatabase?, id : Int){
//        val sql ="DELETE FROM book WHERE  id = " + id + ""
//        db?.execSQL(sql)
//    }


}