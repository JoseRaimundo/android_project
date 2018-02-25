package br.com.pdm.bookbycode

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import br.com.pdm.bookbycode.barcode.BarcodeCaptureActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listBook: ListView
    private lateinit var mResultTextView: TextView
    private var books = arrayListOf<String>("teste")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.listBook = findViewById(R.id.list_book)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books)
        this.listBook.adapter = adapter

        findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
            val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode = data.getParcelableExtra<Barcode>(BarcodeCaptureActivity.BarcodeObject)
                    val p = barcode.cornerPoints

                    add(barcode.displayValue)
                    mResultTextView.setText("Code accepted!")
                }else{
                    mResultTextView.setText("Invalid code!")
                }

            } else
                Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)))
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
        private val BARCODE_READER_REQUEST_CODE = 1
    }

    fun add(code_book: String){
        val st = code_book
        (this.listBook.adapter as ArrayAdapter<String>).add(st)
        //coloca no banco
//        Log.i("APP", this.nomes.toString())
        //this.nomes.add(st)
    }
}
