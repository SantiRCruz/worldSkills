package com.santiago.worldskillscomida.ui.almuerzo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.worldskillscomida.databinding.FragmentAlmuerzoBinding
import com.santiago.worldskillscomida.models.webservices.categoriaId.CategoriaId
import com.santiago.worldskillscomida.ui.CategoriasAdapater


class AlmuerzoFragment : Fragment() {

    private val almuerzoViewModel : AlmuerzoViewModel by viewModels()
    private var _binding: FragmentAlmuerzoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAlmuerzoBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofitListCategory()

    }

    private fun retrofitListCategory() {


        almuerzoViewModel.getListaAlmuerzos().observe(viewLifecycleOwner, Observer {
            when(it){
                is CategoriaId -> {
                    binding.rvAlmuerzo.layoutManager = LinearLayoutManager(requireContext())
                    val adapater = CategoriasAdapater(it.productos)
                    binding.rvAlmuerzo.adapter = adapater
                    binding.progressBarAlmuerzo.visibility = View.GONE
                    binding.rvAlmuerzo.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}