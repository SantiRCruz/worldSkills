package com.santiago.worldskillscomida.models.producto

import com.santiago.worldskillscomida.models.categoriaId.Productos

data class ResponseProducto(

    var respuesta:String =  "",
    var productos:Productos =  Productos()

)
