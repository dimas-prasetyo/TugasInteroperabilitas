package com.epzigsoftwarehouse.tugasinteroperabilitas.RumahSakit

data class RumahSakit(
        val id: Int,
        val nama: String,
        val alamat: String,
        val latitude: String,
        val longtitude: String,
        val gambar: String,
        val keterangan: String,
        val waktu: String,
        val stok: Int,
        val success: Boolean,
        val message: String
)
