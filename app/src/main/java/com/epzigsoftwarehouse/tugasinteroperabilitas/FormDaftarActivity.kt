package com.epzigsoftwarehouse.tugasinteroperabilitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_form_daftar.*

class FormDaftarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar)

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
}