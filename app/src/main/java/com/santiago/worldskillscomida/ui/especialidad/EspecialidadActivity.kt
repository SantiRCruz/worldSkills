package com.santiago.worldskillscomida.ui.especialidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.databinding.ActivityEspecialidadBinding
import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.especialidad.Especialidad
import com.santiago.worldskillscomida.ui.MainActivity
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
        pasarEspecialidad()
    }

    private fun retrofitGetEspeclidad() {

        especialidadViewModel.getEspecialidad().observe(this, Observer {
            when(it){
                is Especialidad ->{
                    binding.tvNombre.text = it.datos.nombre
                    binding.tvDescripcion.text = it.datos.descripcion
                    binding.tvPrecio.text = " $ " + it.datos.precio.toString()
                    Glide.with(applicationContext).load(it.datos.url_foto).into(binding.imgEspecialidad)
                    binding.progressBarEspecialidad.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                }
            }
        })

    }
    private fun pasarEspecialidad(){
        binding.buttonContinuarEspecialidad.setOnClickListener {
            Toast.makeText(applicationContext, ""+Constants.CONTRASENA_RECORDADA, Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}