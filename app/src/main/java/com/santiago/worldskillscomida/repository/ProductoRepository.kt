package com.santiago.worldskillscomida.repository

import com.santiago.worldskillscomida.interfaces.ApiService

class ProductoRepository(private val apiService: ApiService) {

    suspend fun getProductoId(idProducto:Int)=apiService.getProductoId(idProducto)

}