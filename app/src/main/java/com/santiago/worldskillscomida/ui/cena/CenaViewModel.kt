package com.santiago.worldskillscomida.ui.cena

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.webservice.CategoryRepository

class CenaViewModel : ViewModel() {

    private val categoryRepository = CategoryRepository(ApiClient.webService)

    fun getListCategory()= liveData {
        try {
            emit(categoryRepository.getCategoryList(3))
        }catch (e : Exception){
            emit(e)
        }
    }

}