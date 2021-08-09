package com.santiago.worldskillscomida.repository

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.webservices.especialidad.Especialidad

class EspecialidadRepository(private val apiService: ApiService) {

    suspend fun getEspecialidad(): Especialidad = apiService.getEspecialidad()
}