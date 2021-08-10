package com.santiago.worldskillscomida.repository.webservice

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.webservices.categoriaId.CategoriaId

class CategoryRepository(private val apiService: ApiService) {

    suspend fun getCategoryList(id:Int):CategoriaId = apiService.getCategoriaId(id)


}