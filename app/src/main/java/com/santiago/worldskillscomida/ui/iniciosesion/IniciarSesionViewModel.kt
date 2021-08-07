package com.santiago.worldskillscomida.ui.iniciosesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.InicioSesionRepository
import kotlinx.coroutines.Dispatchers

class IniciarSesionViewModel: ViewModel() {

    private val inicioSesionRepository = InicioSesionRepository(ApiClient.webService)

    fun getInicioSesion(correo:String,contrasena:String) = liveData(Dispatchers.IO) {
        try {
            emit(inicioSesionRepository.getInicioSesion(correo,contrasena))
        }catch (e:Exception){

        }
    }

}