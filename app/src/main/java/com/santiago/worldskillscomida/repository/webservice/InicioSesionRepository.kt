package com.santiago.worldskillscomida.repository.webservice

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.webservices.iniciosesion.ResponseInicioSesion

class InicioSesionRepository(private val apiService: ApiService) {

    suspend fun getInicioSesion(correo:String,contrasena:String) : ResponseInicioSesion =apiService.getInicioSesion(correo,contrasena)

}