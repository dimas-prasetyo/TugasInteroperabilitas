package com.epzigsoftwarehouse.tugasinteroperabilitas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import com.epzigsoftwarehouse.tugasinteroperabilitas.database.DatabaseClient
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakit.RumahSakit
import com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakit.RumahSakitAdapter
import kotlinx.android.synthetic.main.activity_rumah_sakit_list.*

class RumahSakitListActivity : AppCompatActivity() {
    private lateinit var rumahSakitAdapter: RumahSakitAdapter
    private lateinit var nik_peserta: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumah_sakit_list)

        nik_peserta = intent.getStringExtra("nik").toString()
        recycler_view_rumah_sakit_list.hasFixedSize()
        recycler_view_rumah_sakit_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        loadRumahSakitList()

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadRumahSakitList() {
        GlobalScope.launch(Dispatchers.IO){
            try {
                val response = DatabaseClient.loadAllRumahSakit().awaitResponse()
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!

                    val rumahSakitList: ArrayList<RumahSakit> = data

                    rumahSakitAdapter = RumahSakitAdapter(this@RumahSakitListActivity, rumahSakitList, nik_peserta)

                    Thread(Runnable {
                        this@RumahSakitListActivity?.runOnUiThread(java.lang.Runnable {
                            recycler_view_rumah_sakit_list.adapter = rumahSakitAdapter
                        })
                    }).start()

                }
            } catch (e: Exception){
                println("Context: " + this@RumahSakitListActivity + "\n Error: " + e)
                e.printStackTrace()
            }
        }
    }
}