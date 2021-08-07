package com.santiago.worldskillscomida.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santiago.worldskillscomida.R

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        this.supportActionBar?.hide()
    }
}