package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_daftar_vaksinasi.*

class DaftarVaksinasiActivity : AppCompatActivity() {
    private var id_rumah_sakit = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_vaksinasi)

        id_rumah_sakit = intent.getStringExtra("id").toString().toInt()

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_daftar.setOnClickListener {
            if (input_nik.text.toString() == "" || input_nik.text == null){
                input_nik.setError("Masukkan NIK")
            } else if (id_rumah_sakit == 0){
                val intent = Intent(this, RumahSakitListActivity::class.java)
                intent.putExtra("nik", input_nik.text.toString())
                intent.putExtra("id", id_rumah_sakit.toString())
                startActivity(intent)
            } else {
                val intent = Intent(this, FormSkriningActivity::class.java)
                intent.putExtra("nik", input_nik.text.toString())
                intent.putExtra("id", id_rumah_sakit.toString())
                startActivity(intent)
            }

        }
    }
}