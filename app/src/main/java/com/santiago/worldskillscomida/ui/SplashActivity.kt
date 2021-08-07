package com.santiago.worldskillscomida.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.ui.iniciosesion.IniciarSesionActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startTimer()
    }

    private fun startTimer() {
        object:CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent = Intent(applicationContext, IniciarSesionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}