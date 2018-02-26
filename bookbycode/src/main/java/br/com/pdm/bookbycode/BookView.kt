package br.com.pdm.bookbycode

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class BookView : AppCompatActivity() {

    private  lateinit var book_view_text : TextView
    private lateinit var delete_book : Button
    private lateinit var id_book : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_view)
        book_view_text = findViewById(R.id.book_view)
        this.book_view_text.text = intent.getStringExtra("BOOK_TEXT")
        this.delete_book = findViewById(R.id.delete_book)

        this.delete_book.setOnClickListener({onClick(it)})
    }

    fun onClick(view: View){
        val it = Intent()
        it.putExtra("DELETE", "OK")
        setResult(Activity.RESULT_OK, it)
        finish()
    }
}
