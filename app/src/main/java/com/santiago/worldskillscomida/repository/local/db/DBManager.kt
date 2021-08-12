package com.santiago.worldskillscomida.repository.local.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.bd.BdBodyProduct
import com.santiago.worldskillscomida.models.webservices.pedido.Pedido

class DBManager(context : Context) {

    val dbHelper= DBHelper(context)
    var db : SQLiteDatabase ?= null

    fun openDbWr(){
        db = dbHelper.writableDatabase
    }
    fun openDbRd(){
        db = dbHelper.readableDatabase
    }
    fun closeDb(){
        if (db!=null){
            db!!.close()
        }
    }
    fun insertData(bdBodyProduct: BdBodyProduct):Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2,bdBodyProduct.idProducto)
        values.put(Constants.TABLE_COLUMN_3,bdBodyProduct.nombre)
        values.put(Constants.TABLE_COLUMN_4,bdBodyProduct.descripcion)
        values.put(Constants.TABLE_COLUMN_5,bdBodyProduct.url_imagen)
        values.put(Constants.TABLE_COLUMN_6,bdBodyProduct.precio_iva_unidad)
        values.put(Constants.TABLE_COLUMN_7,bdBodyProduct.precio_iva_total)
        values.put(Constants.TABLE_COLUMN_8,bdBodyProduct.cantidad)
        val result = db?.insert(Constants.TABLE_NAME,null,values)
        closeDb()
        return  result!!
    }
    fun updateCantidad(id:Int,precio_iva_total : Int,cantidad:Int):Int{
        openDbWr()
        val values = ContentValues()
        values.put(Constants.TABLE_COLUMN_7,precio_iva_total)
        values.put(Constants.TABLE_COLUMN_8,cantidad)
        val result = db?.update(Constants.TABLE_NAME,values," id =? ", arrayOf(id.toString()))
        closeDb()
        return  result!!
    }
     fun totalPrecio():Int {
         openDbRd()
         var valor : Int = 0
         val result = db?.rawQuery(" SELECT SUM ( " + Constants.TABLE_COLUMN_7 + ") FROM " + Constants.TABLE_NAME, null)
         if (result!!.moveToFirst())
             return  result.getInt(0)
         closeDb()
         return valor
     }
    suspend fun listData():MutableList<BdBodyProduct>{
        val list : MutableList<BdBodyProduct> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val bdBodyProduct = BdBodyProduct()
                bdBodyProduct.id = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_1)).toInt()
                bdBodyProduct.idProducto = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_2)).toInt()
                bdBodyProduct.nombre = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                bdBodyProduct.descripcion = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                bdBodyProduct.url_imagen = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                bdBodyProduct.precio_iva_unidad = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_6)).toInt()
                bdBodyProduct.precio_iva_total = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_7)).toInt()
                bdBodyProduct.cantidad = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_8)).toInt()
                list.add(bdBodyProduct)
            }while (result.moveToNext())
        closeDb()
        return list
    }
    fun listDataPedidos():MutableList<Pedido>{
        val list : MutableList<Pedido> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val pedido = Pedido()
                pedido.id_producto = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_2)).toInt()
                pedido.precio = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_6)).toInt()
                pedido.cantidad = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_8)).toInt()
                list.add(pedido)
            }while (result.moveToNext())
        closeDb()
        return list
    }
    fun listAcumulacionPedidos(id_producto:Int):MutableList<BdBodyProduct>{
        val list : MutableList<BdBodyProduct> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL + " WHERE " + Constants.TABLE_COLUMN_2 + " =? " ,
            arrayOf(id_producto.toString()))
        if (result!!.moveToFirst())
            do {
                val bdBodyProduct = BdBodyProduct()
                bdBodyProduct.id = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_1)).toInt()
                bdBodyProduct.idProducto = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_2)).toInt()
                bdBodyProduct.precio_iva_unidad = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_6)).toInt()
                bdBodyProduct.precio_iva_total = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_7)).toInt()
                bdBodyProduct.cantidad = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_8)).toInt()
                list.add(bdBodyProduct)
            }while (result.moveToNext())
        closeDb()
        return list
    }
    fun deleteId(id : Int):Int{
        openDbWr()
        val result = db?.delete(Constants.TABLE_NAME, " id =? ", arrayOf(id.toString()))
        closeDb()
        return result!!
    }
    fun deleteAll():Int{
        openDbWr()
        val result = db?.delete(Constants.TABLE_NAME, null, null)
        closeDb()
        return result!!
    }
}