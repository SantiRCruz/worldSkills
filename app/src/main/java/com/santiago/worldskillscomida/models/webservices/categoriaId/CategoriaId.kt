package com.santiago.worldskillscomida.models.webservices.categoriaId

data class CategoriaId(
    var respuesta: String = "",
    var nombre: String = "",
    var descripcion: String = "",
    var productos: ArrayList<Productos> = ArrayList<Productos>()
)

