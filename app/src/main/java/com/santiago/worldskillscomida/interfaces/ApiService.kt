package com.santiago.worldskillscomida.interfaces

import com.santiago.worldskillscomida.models.categoriaId.CategoriaId
import com.santiago.worldskillscomida.models.especialidad.Especialidad
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("especialidad")
    fun getEspecialidad():Call<Especialidad>

    @GET("categorias/1")
    fun getCategoriaId():Call<CategoriaId>


}