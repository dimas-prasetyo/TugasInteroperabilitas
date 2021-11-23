package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_daftar_vaksinasi.*

class DaftarVaksinasiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_vaksinasi)

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_daftar.setOnClickListener {
            val intent = Intent(this, FormDaftarActivity::class.java)
            startActivity(intent)
        }
    }
}