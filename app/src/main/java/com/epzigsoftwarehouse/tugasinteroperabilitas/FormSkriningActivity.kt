package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epzigsoftwarehouse.tugasinteroperabilitas.database.DatabaseClient
import kotlinx.android.synthetic.main.activity_form_skrining.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class FormSkriningActivity : AppCompatActivity() {
    private lateinit var nik_peserta: String
    private var status_vaksin_1 = 0
    private var status_vaksin_2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_skrining)

        btn_back.setOnClickListener {
            onBackPressed()
        }

        nik_peserta = intent.getStringExtra("nik").toString()

        loadPendudukDetail()
        loadHasilSkrining()

        btn_selanjutnya.setOnClickListener {
            val intent = Intent(this, FormDaftarActivity::class.java)
            intent.putExtra("nik", nik_peserta)
            startActivity(intent)
        }
    }

    private fun loadPendudukDetail() {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.loadPendudukDetail(nik_peserta).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!
                    Thread(Runnable {
                        this@FormSkriningActivity?.runOnUiThread(java.lang.Runnable {

                            txt_nik.text = data.nik
                            txt_nama.text = data.nama

                        })
                    }).start()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

    private fun loadHasilSkrining() {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.loadHasilSkrining(nik_peserta).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!
                    Thread(Runnable {
                        this@FormSkriningActivity?.runOnUiThread(java.lang.Runnable {

                            status_vaksin_1 = data.vaksin_1
                            status_vaksin_2 = data.vaksin_2

                            if (status_vaksin_1 == 0){
                                txt_vaksin.text = "Vaksin Pertama"
                            } else if (status_vaksin_1 == 1 && status_vaksin_2 == 0){
                                txt_vaksin.text = "Vaksin Kedua"
                            } else {
                                txt_vaksin.text = "Telah Melaksanakan Vaksin Dua Kali"
                            }

                            if (data.p1 == 0){
                                txt_p1.text = "Tidak Diketahui"
                            } else if (data.p1 == 1){
                                txt_p1.text = "Ya"
                            } else {
                                txt_p1.text = "Tidak"
                            }

                            if (data.p2 == 0){
                                txt_p2.text = "Tidak Diketahui"
                            } else if (data.p2 == 1){
                                txt_p2.text = "Ya"
                            } else {
                                txt_p2.text = "Tidak"
                            }

                            if (data.p3 == 0){
                                txt_p3.text = "Tidak Diketahui"
                            } else if (data.p3 == 1){
                                txt_p3.text = "Ya"
                            } else {
                                txt_p3.text = "Tidak"
                            }

                            if (data.p4 == 0){
                                txt_p4.text = "Tidak Diketahui"
                            } else if (data.p4 == 1){
                                txt_p4.text = "Ya"
                            } else {
                                txt_p4.text = "Tidak"
                            }

                            if (data.p5 == 0){
                                txt_p5.text = "Tidak Diketahui"
                            } else if (data.p5 == 1){
                                txt_p5.text = "Ya"
                            } else {
                                txt_p5.text = "Tidak"
                            }

                            if (data.p6 == 0){
                                txt_p6.text = "Tidak Diketahui"
                            } else if (data.p6 == 1){
                                txt_p6.text = "Ya"
                            } else {
                                txt_p6.text = "Tidak"
                            }

                            if (data.p7 == 0){
                                txt_p7.text = "Tidak Diketahui"
                            } else if (data.p7 == 1){
                                txt_p7.text = "Ya"
                            } else {
                                txt_p7.text = "Tidak"
                            }

                            if (data.p8 == 0){
                                txt_p8.text = "Tidak Diketahui"
                            } else if (data.p8 == 1){
                                txt_p8.text = "Ya"
                            } else {
                                txt_p8.text = "Tidak"
                            }

                            if (data.p9 == 0){
                                txt_p9.text = "Tidak Diketahui"
                            } else if (data.p9 == 1){
                                txt_p9.text = "Ya"
                            } else {
                                txt_p9.text = "Tidak"
                            }

                            if (data.p10 == 0){
                                txt_p10.text = "Tidak Diketahui"
                            } else if (data.p10 == 1){
                                txt_p10.text = "Ya"
                            } else {
                                txt_p10.text = "Tidak"
                            }

                            if (data.p11 == 0){
                                txt_p11.text = "Tidak Diketahui"
                            } else if (data.p11 == 1){
                                txt_p11.text = "Ya"
                            } else {
                                txt_p11.text = "Tidak"
                            }

                            if (data.p12 == 0){
                                txt_p12.text = "Tidak Diketahui"
                            } else if (data.p12 == 1){
                                txt_p12.text = "Ya"
                            } else {
                                txt_p12.text = "Tidak"
                            }

                            if (data.p13 == 0){
                                txt_p13.text = "Tidak Diketahui"
                            } else if (data.p13 == 1){
                                txt_p13.text = "Ya"
                            } else {
                                txt_p13.text = "Tidak"
                            }

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