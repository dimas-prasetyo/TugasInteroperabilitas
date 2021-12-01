package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epzigsoftwarehouse.tugasinteroperabilitas.database.DatabaseClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_form_daftar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class FormDaftarActivity : AppCompatActivity() {
    private lateinit var nik_peserta: String
    private lateinit var ket_rs: String
    private lateinit var jenis_vaksin: String
    private var id_rumah_sakit = 0
    private var stok_vaksin = 0
    private var status_vaksin = 0
    private var status_vaksin_1 = 0
    private var status_vaksin_2 = 0
    private var radioButton: RadioButton? = null
    private var selectedId = 0
    private lateinit var popUpHasil: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar)

        id_rumah_sakit = intent.getStringExtra("id").toString().toInt()
        nik_peserta = intent.getStringExtra("nik").toString()

        loadHasilSkrining()

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_daftar.setOnClickListener {
            cekForm()
        }

        radio_group_p1.setOnCheckedChangeListener { group, checkedId -> // checkedId is the RadioButton selected
            val rb = findViewById<View>(checkedId) as RadioButton
            var input_p1 = rb.tag.toString().toInt()
            if (input_p1 == 2){
                txt_p2.visibility = View.GONE
                radio_group_p2.visibility = View.GONE
            } else {
                txt_p2.visibility = View.VISIBLE
                radio_group_p2.visibility = View.VISIBLE
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
                        this@FormDaftarActivity?.runOnUiThread(java.lang.Runnable {

                            status_vaksin_1 = data.vaksin_1
                            status_vaksin_2 = data.vaksin_2

                            if (data.p1 == 1) {
                                r_btn_p1_1.isChecked = true
                            } else if (data.p1 == 2) {
                                r_btn_p1_2.isChecked = true
                            }

                            if (data.p2 == 1) {
                                r_btn_p2_1.isChecked = true
                            } else if (data.p2 == 2) {
                                r_btn_p2_2.isChecked = true
                            }


                            if (data.p3 == 1) {
                                r_btn_p3_1.isChecked = true
                            } else if (data.p3 == 2) {
                                r_btn_p3_2.isChecked = true
                            }

                            if (data.p4 == 1) {
                                r_btn_p4_1.isChecked = true
                            } else if (data.p4 == 2) {
                                r_btn_p4_2.isChecked = true
                            }

                            if (data.vaksin_1 == 1){
                                txt_p5.setText(resources.getString(R.string.pertanyaan_5_2))
                            } else {
                                txt_p5.setText(resources.getString(R.string.pertanyaan_5_1))
                            }

                            if (data.p5 == 1) {
                                r_btn_p5_1.isChecked = true
                            } else if (data.p5 == 2) {
                                r_btn_p5_2.isChecked = true
                            }

                            if (data.p6 == 1) {
                                r_btn_p6_1.isChecked = true
                            } else if (data.p6 == 2) {
                                r_btn_p6_2.isChecked = true
                            }

                            if (data.p7 == 1) {
                                r_btn_p7_1.isChecked = true
                            } else if (data.p7 == 2) {
                                r_btn_p7_2.isChecked = true
                            }

                            if (data.p8 == 1) {
                                r_btn_p8_1.isChecked = true
                            } else if (data.p8 == 2) {
                                r_btn_p8_2.isChecked = true
                            }

                            if (data.p9 == 1) {
                                r_btn_p9_1.isChecked = true
                            } else if (data.p9 == 2) {
                                r_btn_p9_2.isChecked = true
                            }

                            if (data.p10 == 1) {
                                r_btn_p10_1.isChecked = true
                            } else if (data.p10 == 2) {
                                r_btn_p10_2.isChecked = true
                            }

                            if (data.p11 == 1) {
                                r_btn_p11_1.isChecked = true
                            } else if (data.p11 == 2) {
                                r_btn_p11_2.isChecked = true
                            }

                            if (data.p12 == 1) {
                                r_btn_p12_1.isChecked = true
                            } else if (data.p12 == 2) {
                                r_btn_p12_2.isChecked = true
                            }

                            if (data.p13 == 1) {
                                r_btn_p13_1.isChecked = true
                            } else if (data.p13 == 2) {
                                r_btn_p13_2.isChecked = true
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

    private fun cekForm() {
        if (radio_group_p1.getCheckedRadioButtonId() == -1) {
            txt_p1.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p2.getCheckedRadioButtonId() == -1) {
            txt_p2.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p3.getCheckedRadioButtonId() == -1) {
            txt_p3.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p4.getCheckedRadioButtonId() == -1) {
            txt_p4.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p5.getCheckedRadioButtonId() == -1) {
            txt_p5.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p6.getCheckedRadioButtonId() == -1) {
            txt_p6.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p7.getCheckedRadioButtonId() == -1) {
            txt_p7.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p8.getCheckedRadioButtonId() == -1) {
            txt_p8.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p9.getCheckedRadioButtonId() == -1) {
            txt_p9.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p10.getCheckedRadioButtonId() == -1) {
            txt_p10.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p11.getCheckedRadioButtonId() == -1) {
            txt_p11.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p12.getCheckedRadioButtonId() == -1) {
            txt_p12.setError("Silahkan Pilih Salah Satu")
        }
        if (radio_group_p13.getCheckedRadioButtonId() == -1) {
            txt_p13.setError("Silahkan Pilih Salah Satu")
        }

        loadRumahSakitDetail()

    }

    private fun loadRumahSakitDetail() {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.loadRumahSakitDetail(id_rumah_sakit).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!

                    stok_vaksin = data.stok
                    ket_rs = data.keterangan
                    jenis_vaksin = data.jenis
                    cekHasilForm()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

    private fun cekHasilForm() {
        // Initiaotion Loading Dialog Box
        val layoutInflater = LayoutInflater.from(this)
        val promptView: View = layoutInflater.inflate(R.layout.layout_hasil_pendaftran, null)
        val layoutLoading = AlertDialog.Builder(this)
        layoutLoading.setCancelable(false)
        layoutLoading.setView(promptView)

        val image_hasil = promptView.findViewById<ImageView>(R.id.image) as ImageView
        val text_hasil = promptView.findViewById<TextView>(R.id.text) as TextView

        // create an loading dialog

        Thread(Runnable {
            this?.runOnUiThread(java.lang.Runnable {
                popUpHasil = layoutLoading.create()
                popUpHasil.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


                if (status_vaksin_2 == 1) {
                    text_hasil.text = "Anda telah melakukan vaksin dua kali"

                    Picasso.get()
                            .load(R.drawable.icon_failed)
                            .into(image_hasil)

                    popUpHasil.show()

                    Handler().postDelayed({
                        popUpHasil.dismiss()
                        onBackPressed()
                    }, 5000)

                } else if (r_btn_p4_1.isChecked == true || r_btn_p6_1.isChecked == true || r_btn_p7_1.isChecked == true) {
                    text_hasil.text = "Maaf anda tidak bisa melakukan vaksinasi"

                    Picasso.get()
                            .load(R.drawable.icon_failed)
                            .into(image_hasil)

                    popUpHasil.show()

                    Handler().postDelayed({
                        popUpHasil.dismiss()
                        onBackPressed()
                    }, 5000)

                } else if (r_btn_p3_1.isChecked == true || r_btn_p2_1.isChecked == true || r_btn_p13_1.isChecked == true){
                    text_hasil.text = "Maaf saat ini anda belum bisa melakukan vaksinasi, silahkan mendaftar lagi dalam 30 hari lagi"

                    Picasso.get()
                            .load(R.drawable.icon_warning)
                            .into(image_hasil)

                    popUpHasil.show()

                    Handler().postDelayed({
                        popUpHasil.dismiss()
                        onBackPressed()
                    }, 5000)

                } else if (r_btn_p5_1.isChecked == true || r_btn_p8_1.isChecked == true || r_btn_p9_1.isChecked == true || r_btn_p10_1.isChecked == true || r_btn_p11_1.isChecked == true || r_btn_p12_1.isChecked == true){
                    if (ket_rs == "Rumah Sakit"){
                        text_hasil.text = "Berhasil melakukan pendaftaran"

                        Picasso.get()
                                .load(R.drawable.icon_done)
                                .into(image_hasil)
                        updateHasilSkrining()

                    } else {
                        text_hasil.text = "Maaf silahkan pilih Rumah Sakit untuk lokasi vaksinasi"

                        Picasso.get()
                                .load(R.drawable.icon_warning)
                                .into(image_hasil)

                        popUpHasil.show()

                        Handler().postDelayed({
                            popUpHasil.dismiss()

                            val intent = Intent(this, RumahSakitListActivity::class.java)
                            intent.putExtra("nik", nik_peserta)
                            intent.putExtra("id", "0")
                            startActivity(intent)
                            finish()
                        }, 5000)
                    }
                } else {
                    text_hasil.text = "Berhasil melakukan pendaftaran"

                    Picasso.get()
                            .load(R.drawable.icon_done)
                            .into(image_hasil)
                    updateHasilSkrining()

                }

            })
        }).start()

    }

    private fun updateHasilSkrining() {
        if (status_vaksin_1 == 0){
            status_vaksin_1 = 1
            status_vaksin_2 = 0
            status_vaksin = 1
        } else {
            status_vaksin_1 = 1
            status_vaksin_2 = 1
            status_vaksin = 2
        }

        // get selected radio button from radioGroup
        selectedId = radio_group_p1.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p1 = radioButton!!.tag.toString().toInt()

        var input_p2 = 0
        try {
            selectedId = radio_group_p2.getCheckedRadioButtonId()
            radioButton = findViewById<View>(selectedId) as RadioButton
            input_p2 = radioButton!!.tag.toString().toInt()
        } catch (e: Exception){
        }

        selectedId = radio_group_p3.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p3 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p4.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p4 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p5.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p5 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p6.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p6 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p7.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p7 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p8.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p8 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p9.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p9 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p10.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p10 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p11.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p11 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p12.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p12 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p13.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p13 = radioButton!!.tag.toString().toInt()

        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.updateSkrining(nik_peserta, status_vaksin_1, status_vaksin_2, input_p1, input_p2, input_p3,
                        input_p4, input_p5, input_p6, input_p7, input_p8, input_p9, input_p10, input_p11, input_p12, input_p13).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!

                    inputRiwayatVaksin()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

    private fun inputRiwayatVaksin() {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.inputRiwayatVaksin(nik_peserta, id_rumah_sakit, status_vaksin, jenis_vaksin).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!

                    updateJumalahVaksin()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

    private fun updateJumalahVaksin() {
        var new_stok = stok_vaksin - 1
        println("stok vaskin: " + stok_vaksin)
        println("stok vaskin baru:  " + new_stok)
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.updateStokVaksin(id_rumah_sakit, new_stok).awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!

                    Thread(Runnable {
                        this@FormDaftarActivity?.runOnUiThread(java.lang.Runnable {
                            popUpHasil.show()

                            Handler().postDelayed({
                                popUpHasil.dismiss()
                                onBackPressed()
                            }, 5000)
                        })
                    }).start()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

    /*override fun onBackPressed() {
        val intent = Intent(this, FormSkriningActivity::class.java)
        intent.putExtra("nik", nik_peserta)
        intent.putExtra("id", id_rumah_sakit.toString())
        startActivity(intent)
        finish()
    }*/
}