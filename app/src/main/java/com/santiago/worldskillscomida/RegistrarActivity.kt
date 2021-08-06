package com.santiago.worldskillscomida

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RegistrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        this.supportActionBar?.hide()
    }
}