package com.santiago.worldskillscomida.ui.pedidos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.santiago.worldskillscomida.interfaces.ApiClient
import com.santiago.worldskillscomida.repository.local.PedidosLocalRepository
import com.santiago.worldskillscomida.repository.local.db.DBManager
import com.santiago.worldskillscomida.repository.webservice.PedidosWebRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class PedidosViewModel() : ViewModel() {

    fun getPedidos(context: Context) = liveData(Dispatchers.IO) {
         val pedidosRepository = PedidosLocalRepository(DBManager(context))
        try {
            emit(pedidosRepository.getPedidos())
        }catch (e:Exception){
            emit(e)
        }
    }

    fun postPedidos(id_cliente:Int,json_pedido:String,total:Int) = liveData(Dispatchers.IO) {
        val pedidosWebRepository = PedidosWebRepository(ApiClient.webService)
        try {
         emit(pedidosWebRepository.postPedidos(id_cliente,json_pedido,total))
        }catch (e:Exception){
            emit(e)
        }
    }


    suspend fun getTotalPedidos(context: Context) = flow {
        val pedidosRepository = PedidosLocalRepository(DBManager(context))
        try {
            emit(pedidosRepository.getTotalPedidos())
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}