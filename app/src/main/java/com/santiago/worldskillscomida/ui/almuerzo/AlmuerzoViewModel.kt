package com.santiago.worldskillscomida.ui.almuerzo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers

class AlmuerzoViewModel : ViewModel() {

    private val categoryRepository = CategoryRepository(ApiClient.webService)

    fun getListaAlmuerzos()= liveData(Dispatchers.IO) {
        try {
            emit(categoryRepository.getCategoryList(2))
        }catch (e : Exception){
            emit(e)
        }
    }

}