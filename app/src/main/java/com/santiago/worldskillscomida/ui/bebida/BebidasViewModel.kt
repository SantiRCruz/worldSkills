package com.santiago.worldskillscomida.ui.bebida

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.webservice.CategoryRepository
import kotlinx.coroutines.Dispatchers

class BebidasViewModel: ViewModel() {

    private val respository = CategoryRepository(ApiClient.webService) // No es Api client es Api service

    fun obtenerListaBebidas () = liveData(Dispatchers.IO) {
        try {
            emit(respository.getCategoryList(4))
        } catch (e : Exception){
            emit(e)
        }
    }
}
