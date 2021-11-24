package com.epzigsoftwarehouse.tugasinteroperabilitas.penduduk

import java.util.*

data class Penduduk(
    val nik: String,
    val nama: String,
    val alamat: String,
    val tanggal_lahir: Date,
    val no_hp: String,
    val jenis_kelamin: String
    )
