package com.santiago.worldskillscomida.ui.pedidos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.santiago.worldskillscomida.repository.local.PedidosRepository
import com.santiago.worldskillscomida.repository.local.db.DBManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class PedidosViewModel() : ViewModel() {

    fun getPedidos(context: Context) = liveData(Dispatchers.IO) {
         val pedidosRepository = PedidosRepository(DBManager(context))
        try {
            emit(pedidosRepository.getPedidos())
        }catch (e:Exception){
            emit(e)
        }
    }

    suspend fun getTotalPedidos(context: Context) = flow {
        val pedidosRepository = PedidosRepository(DBManager(context))
        try {
            emit(pedidosRepository.getTotalPedidos())
        }catch (e : Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}