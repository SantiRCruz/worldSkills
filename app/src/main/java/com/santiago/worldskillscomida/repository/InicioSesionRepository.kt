package com.santiago.worldskillscomida.repository

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.iniciosesion.ResponseInicioSesion

class InicioSesionRepository(private val apiService: ApiService) {

    suspend fun getInicioSesion(correo:String,contrasena:String) : ResponseInicioSesion =apiService.getInicioSesion(correo,contrasena)

}