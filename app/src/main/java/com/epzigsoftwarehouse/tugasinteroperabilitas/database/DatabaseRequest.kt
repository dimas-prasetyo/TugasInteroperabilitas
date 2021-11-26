package com.epzigsoftwarehouse.tugasinteroperabilitas.database

import com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakit.RumahSakit
import com.epzigsoftwarehouse.tugasinteroperabilitas.penduduk.Penduduk
import com.epzigsoftwarehouse.tugasinteroperabilitas.skrining.Skrining
import retrofit2.Call
import retrofit2.http.*

interface DatabaseRequest {

    // Rumah Sakit
    @GET("rumah_sakit_load_all.php")
    fun loadAllRumahSakit() : Call<ArrayList<RumahSakit>>

    @FormUrlEncoded
    @POST("rumah_sakit_load_detail.php")
    fun loadRumahSakitDetail(
        @Field("id") id: Int): Call<RumahSakit>

    @FormUrlEncoded
    @POST("rumah_sakit_load_stok.php")
    fun loadRumahSakitStok(
            @Field("id") id: Int): Call<RumahSakit>

    @FormUrlEncoded
    @POST("rumah_sakit_edit_vaksin.php")
    fun updateStokVaksin(
        @Field("id") id: Int,
        @Field("stok") p13: Int): Call<RumahSakit>

    // Skrining
    @FormUrlEncoded
    @POST("skrining_load_detail.php")
    fun loadHasilSkrining(
            @Field("nik") nik: String): Call<Skrining>

    @FormUrlEncoded
    @POST("skrining_edit.php")
    fun updateSkrining(
        @Field("nik") username: String,
        @Field("vaksin_1") vaksin_1: Int,
        @Field("vaksin_2") vaksin_2: Int,
        @Field("p1") p1: Int,
        @Field("p2") p2: Int,
        @Field("p3") p3: Int,
        @Field("p4") p4: Int,
        @Field("p5") p5: Int,
        @Field("p6") p6: Int,
        @Field("p7") p7: Int,
        @Field("p8") p8: Int,
        @Field("p9") p9: Int,
        @Field("p10") p10: Int,
        @Field("p11") p11: Int,
        @Field("p12") p12: Int,
        @Field("p13") p13: Int): Call<Skrining>


    // Penduduk
    @FormUrlEncoded
    @POST("penduduk_load_detail.php")
    fun loadPendudukDetail(
        @Field("nik") nik: String): Call<Penduduk>
}