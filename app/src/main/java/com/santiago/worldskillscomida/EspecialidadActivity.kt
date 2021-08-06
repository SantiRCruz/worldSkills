package com.santiago.worldskillscomida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.databinding.ActivityEspecialidadBinding
import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.especialidad.Especialidad
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EspecialidadActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEspecialidadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        retrofitGetEspeclidad()
    }

    private fun retrofitGetEspeclidad() {
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        service.getEspecialidad().enqueue(object : Callback<Especialidad>{
            override fun onResponse(call: Call<Especialidad>, response: Response<Especialidad>) {
                Log.e("prueba",response.body()!!.datos.toString())
                binding.tvNombre.text = response.body()!!.datos.nombre
                binding.tvDescripcion.text = response.body()!!.datos.descripcion
                binding.tvPrecio.text = response.body()!!.datos.precio.toString()
                Glide.with(applicationContext).load(response.body()!!.datos.url_foto).into(binding.imgEspecialidad)

            }

            override fun onFailure(call: Call<Especialidad>, t: Throwable) {
                Log.e("Error!",t.message.toString())
            }

        })
    }

}