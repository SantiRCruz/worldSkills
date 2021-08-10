package com.santiago.worldskillscomida.repository.webservice

import com.santiago.worldskillscomida.interfaces.ApiService

class ProductoRepository(private val apiService: ApiService) {

    suspend fun getProductoId(idProducto:Int)=apiService.getProductoId(idProducto)

}