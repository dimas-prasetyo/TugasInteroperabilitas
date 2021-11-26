package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.epzigsoftwarehouse.tugasinteroperabilitas.database.DatabaseClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_rumah_sakit_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.util.*

class RumahSakitDetailActivity : AppCompatActivity() {
    private var id_rumah_sakit = 0
    private var latitude_rs = 0.00
    private var longtitude_rs = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumah_sakit_detail)

        id_rumah_sakit = intent.getStringExtra("id").toString().toInt()
        loadRumahSakitDetail()

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_cek_lokasi.setOnClickListener {
            val uri: String = java.lang.String.format(
                Locale.ENGLISH,
                "http://maps.google.com/maps?q=loc:%f,%f",latitude_rs, longtitude_rs
            )
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            startActivity(intent)
        }

        btn_daftar.setOnClickListener {
            val intent = Intent(this, DaftarVaksinasiActivity::class.java)
            intent.putExtra("id", id_rumah_sakit.toString())
            startActivity(intent)
        }
    }

    private fun loadRumahSakitDetail() {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.loadRumahSakitDetail(id_rumah_sakit).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!


                    Thread(Runnable {
                        this@RumahSakitDetailActivity?.runOnUiThread(java.lang.Runnable {
                            nama_rumah_sakit.text = data.nama
                            alamat.text = data.alamat
                            stok_vaksin.text = "Stok: " + data.stok + " Vaksin"

                            Picasso.get()
                                .load(data.gambar)
                                .placeholder(R.color.colorGray)
                                .error(R.color.colorGray)
                                .into(gambar_rumah_sakit)

                            latitude_rs = data.latitude.toDouble()
                            longtitude_rs = data.longtitude.toDouble()

                        })
                    }).start()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

}