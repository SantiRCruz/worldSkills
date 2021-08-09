package com.santiago.worldskillscomida.interfaces

import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.iniciosesion.ResponseInicioSesion
import com.santiago.worldskillscomida.models.categoriaId.CategoriaId
import com.santiago.worldskillscomida.models.especialidad.Especialidad
import com.santiago.worldskillscomida.models.producto.ResponseProducto
import com.santiago.worldskillscomida.models.registro.BodyRegistro
import com.santiago.worldskillscomida.models.registro.ResponseRegistro
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("productos/{idProducto}")
    suspend fun getProductoId(@Path("idProducto")idProducto:Int):ResponseProducto

    @POST("clientes")
    suspend fun postRegistro(@Body bodyRegistro: BodyRegistro):ResponseRegistro

    @GET("clientes")
    suspend fun getInicioSesion(@Query("correo")correo:String,@Query("contrasena")contrasena:String): ResponseInicioSesion

    @GET("especialidad")
    suspend fun getEspecialidad():Especialidad

    @GET("categorias/{idCategoria}")
    suspend fun getCategoriaId(@Path("idCategoria") idCategoria: Int): CategoriaId

}

object ApiClient {
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java) // No olvidar
    }
}