package com.santiago.worldskillscomida.ui.iniciosesion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.databinding.ActivityIniciarSesionBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.webservices.iniciosesion.ResponseInicioSesion
import com.santiago.worldskillscomida.ui.especialidad.EspecialidadActivity
import com.santiago.worldskillscomida.ui.registrar.RegistroActivity

class IniciarSesionActivity : AppCompatActivity() {

    private val iniciarSesionViewModel : IniciarSesionViewModel by viewModels()
    private lateinit var binding : ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()


        iniciarSesion()
        pasarRegistro()

    }



    private fun iniciarSesion() {
        binding.buttonInicioSesion.setOnClickListener {view->
//            if (binding.cbRecordarContrasena.isChecked) Toast.makeText(applicationContext, "se recordo contraseña", Toast.LENGTH_SHORT)
//                .show()
            iniciarSesionViewModel.getInicioSesion(binding.etCorreo.text.toString(),binding.etConstrasena.text.toString()).observe(this,
                Observer {
                    when (it){
                        is ResponseInicioSesion ->{
                            if (it.respuesta=="OK"){
                                Constants.CONTRASENA_RECORDADA=1
                                Constants.ID_CLIENTE=it.idCliente
                                Log.e("InicioSesion",it.toString())
                                val intent = Intent(applicationContext, EspecialidadActivity::class.java)
                                startActivity(intent)
                                finish()
                            }else{
                                 Snackbar.make(view,"Error!!, datos incorrectos",Snackbar.LENGTH_LONG).show()
                            }

                        }
                        else -> Snackbar.make(view,"Error!!, datos incorrectos",Snackbar.LENGTH_LONG).show()
                    }
                })
        }
    }
    private fun pasarRegistro(){
        binding.buttonRegistrar.setOnClickListener {
            val intent = Intent(applicationContext,RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}