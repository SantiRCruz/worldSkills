package com.santiago.worldskillscomida.repository

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.registro.BodyRegistro
import com.santiago.worldskillscomida.models.registro.ResponseRegistro

class RegistroRepository(private val apiService: ApiService) {

    suspend fun postRegistro(nombre:String,correo:String,contrasena:String,ciudad:String) : ResponseRegistro= apiService.postRegistro(BodyRegistro(nombre,correo,contrasena,ciudad))

}