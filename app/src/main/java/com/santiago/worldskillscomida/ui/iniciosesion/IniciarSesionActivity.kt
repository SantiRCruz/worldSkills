package com.santiago.worldskillscomida.ui.iniciosesion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.databinding.ActivityIniciarSesionBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.webservices.iniciosesion.ResponseInicioSesion
import com.santiago.worldskillscomida.ui.especialidad.EspecialidadActivity
import com.santiago.worldskillscomida.ui.registrar.RegistroActivity
import android.preference.PreferenceManager as PreferenceManager1

class IniciarSesionActivity : AppCompatActivity() {

    private val iniciarSesionViewModel: IniciarSesionViewModel by viewModels()
    private lateinit var binding: ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        loadData()
        iniciarSesion()
        pasarRegistro()

    }

    private fun iniciarSesion() {
        binding.buttonInicioSesion.setOnClickListener { view ->
            getInicioSesion(view)
        }
    }

    private fun getInicioSesion(view: View) {
        iniciarSesionViewModel.getInicioSesion(
            binding.etCorreo.text.toString(),
            binding.etConstrasena.text.toString()
        ).observe(this,
            Observer {
                when (it) {
                    is ResponseInicioSesion -> {
                        if (it.respuesta == "OK") {
                            saveData()
                            saveActivo()
                            Constants.CONTRASENA_RECORDADA = 1
                            Constants.ID_CLIENTE = it.idCliente
                            Log.e("InicioSesion", it.toString())
                            val intent =
                                Intent(applicationContext, EspecialidadActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Snackbar.make(view, "Error!!, datos incorrectos", Snackbar.LENGTH_LONG)
                                .show()
                        }

                    }
                    else -> Snackbar.make(view, "Error!!, datos incorrectos", Snackbar.LENGTH_LONG)
                        .show()
                }
            })
    }

    private fun saveActivo(){
        val sharedPreferences = getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putBoolean(Constants.KEY_PERMANECER_ACTIVO, true)
        }.apply()
    }

    private fun saveData(){
        val sharedPreferences = getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (binding.cbRecordarContrasena.isChecked) {
            editor.apply {
                putString(Constants.KEY_CORREO, binding.etCorreo.text.toString())
                putString(Constants.KEY_CONTRASENA, binding.etConstrasena.text.toString())
                putBoolean(Constants.KEY_RECORDAR_CONTRASENA, binding.cbRecordarContrasena.isChecked)
            }.apply()
        }
        if (!binding.cbRecordarContrasena.isChecked) {
            editor.apply {
                putString(Constants.KEY_CORREO, "")
                putString(Constants.KEY_CONTRASENA,"" )
                putBoolean(Constants.KEY_RECORDAR_CONTRASENA, false)
            }.apply()
        }
    }
    private fun loadData(){
        val sharedPreferences = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val correo = sharedPreferences.getString(Constants.KEY_CORREO,null)
        val contrasena = sharedPreferences.getString(Constants.KEY_CONTRASENA,null)
        val cbRecordar = sharedPreferences.getBoolean(Constants.KEY_RECORDAR_CONTRASENA,false)
        binding.etCorreo.setText(correo)
        binding.etConstrasena.setText(contrasena)
        if (cbRecordar)  binding.cbRecordarContrasena.isChecked  = true

    }

    private fun pasarRegistro() {
        binding.buttonRegistrar.setOnClickListener {
            val intent = Intent(applicationContext, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}