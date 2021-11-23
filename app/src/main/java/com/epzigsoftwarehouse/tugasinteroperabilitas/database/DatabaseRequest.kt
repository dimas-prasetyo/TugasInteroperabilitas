package com.epzigsoftwarehouse.tugasinteroperabilitas.database

import com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakit.RumahSakit
import retrofit2.Call
import retrofit2.http.*

interface DatabaseRequest {

    @GET("rumah_sakit_load_all.php")
    fun loadAllRumahSakit() : Call<ArrayList<RumahSakit>>

    @FormUrlEncoded
    @POST("rumah_sakit_load_detail.php")
    fun loadRumahSakitDetail(
        @Field("id") id: Int): Call<RumahSakit>
}