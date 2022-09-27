package com.example.aula_retrofit.services

import com.example.aula_retrofit.models.LoginRequest
import com.example.aula_retrofit.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Auth {

    @POST("/auth/login")
    fun login(@Body body: LoginRequest): Call<LoginResponse>

}
