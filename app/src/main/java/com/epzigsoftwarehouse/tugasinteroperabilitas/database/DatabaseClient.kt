package com.epzigsoftwarehouse.tugasinteroperabilitas.database

import com.epzigsoftwarehouse.tugasinteroperabilitas.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val DatabaseClient = Retrofit.Builder()
        .baseUrl(BuildConfig.DB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DatabaseRequest::class.java)