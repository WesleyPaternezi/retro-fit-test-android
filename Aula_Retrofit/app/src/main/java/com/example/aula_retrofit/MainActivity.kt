package com.example.aula_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.aula_retrofit.databinding.ActivityMainBinding
import com.example.aula_retrofit.models.Filme
import com.example.aula_retrofit.models.LoginRequest
import com.example.aula_retrofit.models.LoginResponse
import com.example.aula_retrofit.rest.Rest
import com.example.aula_retrofit.services.Auth
import com.example.aula_retrofit.services.Filmes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            tryLogin()
        }
    }

    fun tryLogin() {
        val email = binding.emailET.text.toString()
        val password = binding.passwordET.text.toString()
        val body = LoginRequest(email, password)

        val request = Rest.getInstance().create(Auth::class.java)

        val requestFilme = Rest.getInstance().create(Filmes::class.java)

        request.login(body).enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Toast.makeText(
                    baseContext,
                    response.body()?.token,
                    Toast.LENGTH_LONG
                ).show()

                requestFilme.listFilmes("Bearer ${response.body()!!.token}").enqueue(object: Callback<List<Filme>>{
                    override fun onResponse(
                        call: Call<List<Filme>>,
                        response: Response<List<Filme>>
                    ) {
                        response.body()!!.forEach{
                            binding.txtListFilmes.text = "${binding.txtListFilmes.text.toString() + it.title}\n"
                        }
                        println(response.body()!!.first().title)
                    }

                    override fun onFailure(call: Call<List<Filme>>, t: Throwable) {
                        binding.txtErrorMessage.text= t.message
                    }
                })
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.txtErrorMessage.text= t.message
                println(t)
            }
        })
    }

    fun showFilmes(token : String ) {
        val request = Rest.getInstance().create(Filmes::class.java)

    }
}