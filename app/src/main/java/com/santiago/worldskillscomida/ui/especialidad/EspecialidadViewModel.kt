package com.santiago.worldskillscomida.ui.especialidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.EspecialidadRepository

class EspecialidadViewModel: ViewModel() {

    private val especialidadRepository = EspecialidadRepository(ApiClient.webService)

    fun getEspecialidad() = liveData {
        try {
            emit(especialidadRepository.getEspecialidad())
        }catch (e : Exception){
            emit(e)
        }
    }

}