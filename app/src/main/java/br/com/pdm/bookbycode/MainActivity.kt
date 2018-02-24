package br.com.pdm.bookbycode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.pdm.bookbycode.R
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import br.com.pdm.bookbycode.barcode.BarcodeCaptureActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
