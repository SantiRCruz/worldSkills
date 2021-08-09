package com.santiago.worldskillscomida.models.webservices.iniciosesion

data class ResponseInicioSesion(
    var respuesta:String = "",
    var nombre:String = "",
    var idCliente:Int = 0,
    var token:String = ""
)
