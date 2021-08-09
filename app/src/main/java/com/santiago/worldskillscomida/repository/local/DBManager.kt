package com.santiago.worldskillscomida.repository.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.bd.BdBodyProduct

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
        values.put(Constants.TABLE_COLUMN_6,bdBodyProduct.precio_iva)
        values.put(Constants.TABLE_COLUMN_7,bdBodyProduct.cantidad)
        val result = db?.insert(Constants.TABLE_NAME,null,values)
        closeDb()
        return  result!!
    }
    fun listData():MutableList<BdBodyProduct>{
        val list : MutableList<BdBodyProduct> = arrayListOf()
        openDbRd()
        val result = db?.rawQuery(Constants.QUERY_ALL,null)
        if (result!!.moveToFirst())
            do {
                val bdBodyProduct = BdBodyProduct()
                bdBodyProduct.idProducto = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_2)).toInt()
                bdBodyProduct.nombre = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                bdBodyProduct.descripcion = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                bdBodyProduct.url_imagen = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                bdBodyProduct.precio_iva = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_6)).toInt()
                bdBodyProduct.cantidad = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_7)).toInt()
                list.add(bdBodyProduct)
            }while (result.moveToNext())
        closeDb()
        return list
    }
    fun delete(id : Int):Int{
        val result = db?.delete(Constants.TABLE_NAME, " id =? ", arrayOf(id.toString()))
        return result!!
    }
}