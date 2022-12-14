package com.example.aula_retrofit.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    private val baseURL = "http://10.0.2.2:3000"

    fun getInstance(): Retrofit {
        return Retrofit
            .Builder().baseUrl(this.baseURL)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }
}