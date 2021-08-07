package com.santiago.worldskillscomida.ui.desayuno

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers

class DesayunoViewModel:ViewModel() {

    private var categoryRepository = CategoryRepository(ApiClient.webService)

    fun getListCategory() = liveData(Dispatchers.IO) {
        try {
            emit(categoryRepository.getCategoryList(1))
        }catch (e:Exception){
            emit(e)
        }
    }
}