package com.santiago.worldskillscomida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EspecialidadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_especialidad)
        this.supportActionBar?.hide()
    }
}