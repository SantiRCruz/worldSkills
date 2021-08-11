package com.santiago.worldskillscomida.models.webservices.pedido

data class BodyPedidos(
    var id_cliente : Int = 0,
    var json_pedido:String = "" ,
        //"[{\"id_producto\":17,\"cantidad\":2,\"precio\":3000},{\"id_producto\":17,\"cantidad\":2,\"precio\":3000}]",
    var total : Int = 0
)


