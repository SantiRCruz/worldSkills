package com.santiago.worldskillscomida.ui.bebida

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import com.santiago.worldskillscomida.databinding.FragmentBebidasBinding
import com.santiago.worldskillscomida.models.categoriaId.CategoriaId
import com.santiago.worldskillscomida.ui.CategoriasAdapater

class BebidasFragment : Fragment() {

    private val bebidasViewModel: BebidasViewModel by viewModels()
    private var _binding: FragmentBebidasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBebidasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        retrofitListCategory()
    }

    private fun retrofitListCategory() {

        bebidasViewModel.obtenerListaBebidas().observe(viewLifecycleOwner, Observer {
            when (it) {
                is CategoriaId -> {
                    val lista = it.productos
                    binding.rvBebidas.layoutManager = GridLayoutManager(requireContext(), 2)
                    val adapter = CategoriasAdapater(lista)
                    binding.rvBebidas.adapter = adapter
                    binding.progressBarBebidas.visibility = View.GONE
                    binding.rvBebidas.visibility = View.VISIBLE
                }
                else -> {
                    Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}