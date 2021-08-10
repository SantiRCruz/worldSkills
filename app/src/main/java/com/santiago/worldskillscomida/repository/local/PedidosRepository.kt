package com.santiago.worldskillscomida.repository.local

import com.santiago.worldskillscomida.models.bd.BdBodyProduct
import com.santiago.worldskillscomida.repository.local.db.DBManager

class PedidosRepository(private val dbManager: DBManager) {

    suspend fun getPedidos() : List<BdBodyProduct> = dbManager.listData()

}