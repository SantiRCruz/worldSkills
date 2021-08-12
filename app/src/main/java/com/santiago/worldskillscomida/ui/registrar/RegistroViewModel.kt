package com.santiago.worldskillscomida.ui.registrar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.webservice.RegistroRepository
import kotlinx.coroutines.Dispatchers

class RegistroViewModel : ViewModel(){

    private val registroRepository = RegistroRepository(ApiClient.webService)

    fun postRegistro(nombre:String,correo:String,contrasena:String,ciudad:String) = liveData(Dispatchers.IO) {
        try {
            emit(registroRepository.postRegistro(nombre,correo,contrasena,ciudad))
        }catch (e:Exception){
            emit(e)
        }
    }
    fun getPoliticas()= liveData(Dispatchers.IO) {
        try {
            emit(registroRepository.getPoliticas())
        }catch (e : Exception){
            emit(e)
        }
    }

}