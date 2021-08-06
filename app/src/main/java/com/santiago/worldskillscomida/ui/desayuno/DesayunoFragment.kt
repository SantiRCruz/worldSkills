package com.santiago.worldskillscomida.ui.desayuno

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.santiago.worldskillscomida.CategoriasAdapater
import com.santiago.worldskillscomida.databinding.FragmentDesayunoBinding
import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.categoriaId.CategoriaId
import com.santiago.worldskillscomida.models.categoriaId.Productos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DesayunoFragment : Fragment() {

    private var _binding: FragmentDesayunoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDesayunoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitListCategory()

    }

    private fun retrofitListCategory() {
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        service.getCategoriaId().enqueue(object : Callback<CategoriaId> {
            override fun onResponse(call: Call<CategoriaId>, response: Response<CategoriaId>) {
                Log.e("prueba",response.body().toString())


            }

            override fun onFailure(call: Call<CategoriaId>, t: Throwable) {
                Log.e("Error!",t.message.toString())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}