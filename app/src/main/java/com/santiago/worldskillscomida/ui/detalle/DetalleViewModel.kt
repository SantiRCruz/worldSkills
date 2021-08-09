package com.santiago.worldskillscomida.ui.detalle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.ProductoRepository
import kotlinx.coroutines.Dispatchers

class DetalleViewModel : ViewModel() {

    private val productoRepository = ProductoRepository(ApiClient.webService)

    fun getProductoId(idProducto:Int)= liveData(Dispatchers.IO) {
        try {
            emit(productoRepository.getProductoId(idProducto))
        }catch (e:Exception){
            emit(e)
        }
    }


}