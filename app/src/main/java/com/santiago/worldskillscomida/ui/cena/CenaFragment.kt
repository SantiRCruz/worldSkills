package com.santiago.worldskillscomida.ui.cena

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.santiago.worldskillscomida.ui.CategoriasAdapater
import com.santiago.worldskillscomida.databinding.FragmentCenaBinding
import com.santiago.worldskillscomida.interfaces.ApiService
import com.santiago.worldskillscomida.models.categoriaId.CategoriaId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CenaFragment : Fragment() {

    private val cenaViewModel : CenaViewModel by viewModels()
    private var _binding: FragmentCenaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCenaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitListCategory()
    }

    private fun retrofitListCategory() {

        cenaViewModel.getListCategory().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when(it){
                is CategoriaId ->{
                    binding.rvCena.layoutManager = GridLayoutManager(requireContext(),2)
                    val adapter = CategoriasAdapater(it.productos)
                    binding.rvCena.adapter=adapter
                    binding.progressBarCena.visibility = View.GONE
                    binding.rvCena.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}