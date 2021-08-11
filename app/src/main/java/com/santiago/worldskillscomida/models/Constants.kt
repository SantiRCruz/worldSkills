package com.santiago.worldskillscomida.models

class Constants {

    companion object{

        val API_URL = "https://wsc.fabricasoftware.co/api/"

        val KEY_CORREO = "contrasena  "
        val KEY_CONTRASENA = "contrasena"
        val KEY_RECORDAR_CONTRASENA = "recordar"
        val KEY_PERMANECER_ACTIVO = "activo"

        var CONTRASENA_RECORDADA = 0

        //para listar el detalle
        var ID_PRODUCTO = 0

        // id para pedidos
        var ID_CLIENTE = 0

        //BD

        val DB_NAME = "pedidos"
        val DB_VERSION = 4

        val TABLE_NAME = "productos"
        val TABLE_COLUMN_1 = "id"
        val TABLE_COLUMN_2 = "idProducto"
        val TABLE_COLUMN_3 = "nombre"
        val TABLE_COLUMN_4 = "descripcion"
        val TABLE_COLUMN_5 = "url_imagen"
        val TABLE_COLUMN_6 = "precio_iva_unidad"
        val TABLE_COLUMN_7 = "precio_iva_total"
        val TABLE_COLUMN_8 = "cantidad"


        val TABLE_CREATE = " CREATE TABLE " + TABLE_NAME + " ( " +
                TABLE_COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                TABLE_COLUMN_2 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_3 + " TEXT NOT NULL , " +
                TABLE_COLUMN_4 + " TEXT NOT NULL , " +
                TABLE_COLUMN_5 + " TEXT NOT NULL , " +
                TABLE_COLUMN_6 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_7 + " INTEGER NOT NULL , " +
                TABLE_COLUMN_8 + " INTEGER NOT NULL) "

        val QUERY_ALL = " SELECT * FROM " + TABLE_NAME

    }

}