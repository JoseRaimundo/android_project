package br.com.pdm.bookbycode

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import br.com.pdm.bookbycode.database.FacadeBook
import br.com.pdm.bookbycode.model.Book
import com.dd.processbutton.iml.SubmitProcessButton

class MainActivity : AppCompatActivity() {
    private lateinit var listBook: ListView
    private lateinit var mResultTextView: TextView
    private lateinit var mBtnAction: SubmitProcessButton
    private val BOOK_VIEW = 2
    private var facede_book = FacadeBook(this)
    private lateinit var book_for_delete : Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.mResultTextView = findViewById(R.id.result_textview)
        this.listBook = findViewById(R.id.list_book)
        this.mBtnAction = findViewById(R.id.scan_barcode_button);
        findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
            val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE)
        }

        this.updateList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode = data.getParcelableExtra<Barcode>(BarcodeCaptureActivity.BarcodeObject)
                    if(facede_book.addBook(barcode.displayValue)){
                        this.updateList()
                        mResultTextView.setText("Code accepted!")
                    }else{
                        mResultTextView.setText("Invalid code!")
                    }
                }else{
                    mResultTextView.setText("No QR Code identify!")
                }
            } else
                Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)))
        }else if(requestCode == BOOK_VIEW){
            if (resultCode == Activity.RESULT_OK){
                val msg = data?.getStringExtra("DELETE")
                if (msg.toString().equals("OK")){
                    facede_book.delete(book_for_delete)
                    this.updateList()
                }

            }

        }else
            super.onActivityResult(requestCode, resultCode, data)



    }

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
        private val BARCODE_READER_REQUEST_CODE = 1
    }

    fun updateList(){

        var list = this.facede_book.bookList()
        val adapter = ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, list)
        this.listBook.setOnItemClickListener({parent, view, position, id ->
            requestBook(list[position].url_book)
        })
        this.listBook.setOnItemLongClickListener({parent, view, position, id ->
            deleteBook(list[position]);
        })

        mBtnAction.setProgress(list.size * 10);
        this.listBook.adapter = adapter
    }

    fun requestBook(url_book : String){
        //regex na string

        var url = url_book;
        if (!url_book.startsWith("http://") && !url_book.startsWith("https://")) {
            url = "http://" + url_book;
        }
        val uri = Uri.parse(url_book)
        var it = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(it)

    }

    fun deleteBook(book: Book): Boolean{
        val it = Intent(this, BookView::class.java)
        it.putExtra("BOOK_TEXT", book.getFullString())
        book_for_delete = book
        startActivityForResult(it, BOOK_VIEW)
        return true
    }


}

