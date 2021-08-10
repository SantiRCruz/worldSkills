package com.santiago.worldskillscomida.models.bd

data class BdBodyProduct(
    var id : Int = 0,
    var idProducto : Int = 0,
    var nombre:String = "",
    var descripcion :String = "",
    var url_imagen :String = "",
    var precio_iva_unidad:Int = 0,
    var precio_iva_total:Int = 0,
    var cantidad : Int = 0
)
