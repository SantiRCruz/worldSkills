package com.santiago.worldskillscomida.ui.registrar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.databinding.ActivityRegistroBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.webservices.registro.ResponseRegistro
import com.santiago.worldskillscomida.ui.especialidad.EspecialidadActivity
import com.santiago.worldskillscomida.ui.iniciosesion.IniciarSesionActivity

class RegistroActivity : AppCompatActivity() {

    private val registroViewModel : RegistroViewModel by viewModels()
    private lateinit var binding : ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        pasarInicioSesion()
        registrarse()

    }

    private fun registrarse() {
        binding.buttonRegistrarseRegistro.setOnClickListener { view->
            registroViewModel.postRegistro(binding.etNombre.text.toString(),
                binding.etCorreo.text.toString(),
                binding.etConstrasena.text.toString(),
                binding.etCiudad.text.toString()).observe(this, Observer {
                Log.e("Registrado",it.toString())
                when(it){
                        is ResponseRegistro ->{
                            if (it.respuesta == "OK"){
                                Constants.CONTRASENA_RECORDADA = 1
                                val intent = Intent(applicationContext, EspecialidadActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                Snackbar.make(view,"Error!!, ya se encuentra registrado", Snackbar.LENGTH_LONG).show()
                            }
                        }else-> Snackbar.make(view,"Error!!, datos incorrectos", Snackbar.LENGTH_LONG).show()

                    }
            })
        }
    }

    private fun pasarInicioSesion() {
        binding.buttonInicioSesionRegistro.setOnClickListener {
            val intent = Intent(applicationContext,IniciarSesionActivity::class.java)
            startActivity(intent)
        }
    }


}