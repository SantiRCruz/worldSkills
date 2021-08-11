package com.santiago.worldskillscomida.repository.webservice

import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.webservices.pedido.BodyPedidos
import retrofit2.http.Body

class PedidosWebRepository(private val apiService: ApiService) {

    suspend fun postPedidos(id_cliente:Int,json_pedido:String,total:Int) = apiService.postPedidos(BodyPedidos(id_cliente,json_pedido,total))

}