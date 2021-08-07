package com.santiago.worldskillscomida.repository

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.categoriaId.CategoriaId
import com.santiago.worldskillscomida.models.especialidad.Especialidad

class CategoryRepository(private val apiService: ApiService) {

    suspend fun getCategoryList(id:Int):CategoriaId = apiService.getCategoriaId(id)


}