package com.epzigsoftwarehouse.tugasinteroperabilitas

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.epzigsoftwarehouse.tugasinteroperabilitas.database.DatabaseClient
import kotlinx.android.synthetic.main.activity_form_daftar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class FormDaftarActivity : AppCompatActivity() {
    private lateinit var nik_peserta: String
    private var status_vaksin_1 = 0
    private var status_vaksin_2 = 0
    private var radioButton: RadioButton? = null
    private var selectedId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar)

        nik_peserta = intent.getStringExtra("nik").toString()

        loadHasilSkrining()

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_daftar.setOnClickListener {
            cekForm()
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
        if (status_vaksin_1 == 1 && status_vaksin_2 == 1){

        } else {
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

            updateHasilSkrining()
        }

    }

    private fun updateHasilSkrining() {
        if (status_vaksin_1 == 0){
            status_vaksin_1 = 1
            status_vaksin_2 = 0
        } else {
            status_vaksin_1 = 1
            status_vaksin_2 = 1
        }

        // get selected radio button from radioGroup

        // get selected radio button from radioGroup
        selectedId = radio_group_p1.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p1 = radioButton!!.tag.toString().toInt()

        selectedId = radio_group_p2.getCheckedRadioButtonId()
        radioButton = findViewById<View>(selectedId) as RadioButton
        var input_p2 = radioButton!!.tag.toString().toInt()

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

                    println("Berhasil Menambahkan")
                    updateJumalahVaksin()
                }
            } catch (e: Exception){
                println("Context: " + this + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }

    private fun updateJumalahVaksin() {

    }
}