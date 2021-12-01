package com.epzigsoftwarehouse.tugasinteroperabilitas.vaksinasi

data class Vaksinasi(
        val id: Int,
        val nik: String,
        val rs_id: Int,
        val vaksin: Int,
        val jenis: String,
        val success: Boolean,
        val message: String
)