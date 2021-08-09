package com.santiago.worldskillscomida.models.webservices.producto

import com.santiago.worldskillscomida.models.webservices.categoriaId.Productos

data class ResponseProducto(

    var respuesta:String =  "",
    var productos:Productos =  Productos()

)
