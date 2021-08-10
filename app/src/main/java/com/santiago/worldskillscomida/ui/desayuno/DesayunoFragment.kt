package com.santiago.worldskillscomida.ui.desayuno

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.worldskillscomida.ui.CategoriasAdapater
import com.santiago.worldskillscomida.databinding.FragmentDesayunoBinding
import com.santiago.worldskillscomida.models.webservices.categoriaId.CategoriaId

class DesayunoFragment : Fragment() {

    private val desayunoViewModel : DesayunoViewModel by viewModels()
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

        desayunoViewModel.getListCategory().observe(viewLifecycleOwner, Observer {
            when(it){
                is CategoriaId  ->{
                    binding.rvDesayuno.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvDesayuno.adapter = CategoriasAdapater(it.productos)
                    binding.progressBarDesayuno.visibility = View.GONE
                    binding.rvDesayuno.visibility = View.VISIBLE
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}