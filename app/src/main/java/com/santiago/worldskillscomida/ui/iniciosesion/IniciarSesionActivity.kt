package com.santiago.worldskillscomida.ui.iniciosesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.ActivityIniciarSesionBinding
import com.santiago.worldskillscomida.models.ResponseInicioSesion
import com.santiago.worldskillscomida.ui.RegistrarActivity
import com.santiago.worldskillscomida.ui.especialidad.EspecialidadActivity

class IniciarSesionActivity : AppCompatActivity() {

    private val iniciarSesionViewModel : IniciarSesionViewModel by viewModels()
    private lateinit var binding : ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        pasarRegistro()
        iniciarSesion()

    }

    private fun iniciarSesion() {

        binding.buttonIniciarSesion.setOnClickListener {view->
            iniciarSesionViewModel.getInicioSesion(binding.etCorreo.text.toString(),binding.etConstrasena.text.toString()).observe(this,
                Observer {
                    when (it){
                        is ResponseInicioSesion ->{
                            val intent = Intent(applicationContext,EspecialidadActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else -> Snackbar.make(view,"Error!!, datos incorrectos",Snackbar.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun pasarRegistro() {
        binding.buttonRegistrarse.setOnClickListener {
            val intent = Intent(applicationContext, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }
}