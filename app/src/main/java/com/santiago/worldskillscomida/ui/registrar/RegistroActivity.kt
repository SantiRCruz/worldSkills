package com.santiago.worldskillscomida.ui.registrar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.databinding.ActivityRegistroBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.webservices.politicas.ResponsePoliticas
import com.santiago.worldskillscomida.models.webservices.registro.ResponseRegistro
import com.santiago.worldskillscomida.ui.DialogRegistro
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
            var dialog = DialogRegistro()
            if (binding.etCiudad.text.toString().isEmpty()||binding.etConstrasena.text.toString().isEmpty()||binding.etCorreo.text.toString().isEmpty()||binding.etNombre.text.toString().isEmpty()){
                Snackbar.make(view,"No se puede registrar", Snackbar.LENGTH_LONG).show()
            }else{
                registroViewModel.getPoliticas().observe(this, Observer {
                    when(it){
                        is ResponsePoliticas ->{
                            Constants.POLITICAS_PRIVACIDAD=it.datos.politicas
                            dialog.show(supportFragmentManager,"dialogRegistro")
                            if(Constants.POLITICAS_ELECCION==1){
                                postRegistro(view)
                                Constants.POLITICAS_ELECCION=0
                            }else if(Constants.POLITICAS_ELECCION==2){
                                Snackbar.make(view,"No se pudo crear la cuenta, usted rechzo los terminos de politica y privacidad", Snackbar.LENGTH_LONG).show()
                                Constants.POLITICAS_ELECCION=0

                            }
                        }
                    }
                })
            }


        }
    }

    private fun pasarInicioSesion() {
        binding.buttonInicioSesionRegistro.setOnClickListener {
            val intent = Intent(applicationContext,IniciarSesionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun postRegistro(view:View){
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