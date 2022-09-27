package com.example.aula_retrofit.services

import com.example.aula_retrofit.models.Filme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface Filmes {

    @GET("/filmes")
    fun listFilmes(
        @Header("Authorization")
        token:String
    ): Call<List<Filme>>
}