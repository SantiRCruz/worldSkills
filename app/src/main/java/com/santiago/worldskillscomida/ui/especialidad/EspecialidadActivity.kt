package com.santiago.worldskillscomida.ui.especialidad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.databinding.ActivityEspecialidadBinding
import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.especialidad.Especialidad
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EspecialidadActivity : AppCompatActivity() {

    private val especialidadViewModel : EspecialidadViewModel by viewModels()
    private lateinit var binding : ActivityEspecialidadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        retrofitGetEspeclidad()
    }

    private fun retrofitGetEspeclidad() {

        especialidadViewModel.getEspecialidad().observe(this, Observer {
            when(it){
                is Especialidad ->{
                    binding.tvNombre.text = it.datos.nombre
                    binding.tvDescripcion.text = it.datos.descripcion
                    binding.tvPrecio.text = "$ "+ it.datos.precio.toString()
                    Glide.with(applicationContext).load(it.datos.url_foto).into(binding.imgEspecialidad)
                    binding.progressBarEspecialidad.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                }
            }
        })

    }

}