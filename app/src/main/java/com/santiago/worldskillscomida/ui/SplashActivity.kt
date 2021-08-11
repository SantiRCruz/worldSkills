package com.santiago.worldskillscomida.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.repository.local.db.DBHelper
import com.santiago.worldskillscomida.ui.especialidad.EspecialidadActivity
import com.santiago.worldskillscomida.ui.iniciosesion.IniciarSesionActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val dbHelper = DBHelper(applicationContext)
        dbHelper.writableDatabase

        startTimer()
    }

    private fun startTimer() {
        object:CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
               val sharedPreference = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
                var activo = sharedPreference.getBoolean(Constants.KEY_PERMANECER_ACTIVO,false)
                if (activo){
                    val intent = Intent(applicationContext, EspecialidadActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(applicationContext, IniciarSesionActivity::class.java)
                    startActivity(intent)
                    finish()
                }


            }
        }.start()
    }
}