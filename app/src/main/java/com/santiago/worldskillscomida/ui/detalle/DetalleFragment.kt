package com.santiago.worldskillscomida.ui.detalle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.databinding.FragmentDetalleBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.producto.ResponseProducto


class DetalleFragment : Fragment() {

    private val detalleViewModel : DetalleViewModel by viewModels()
    private var _binding: FragmentDetalleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetalleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detalleViewModel.getProductoId(Constants.ID_PRODUCTO).observe(viewLifecycleOwner, Observer {
            when(it){
                is ResponseProducto->{
                    binding.tvNombre.text = it.productos.nombre
                    binding.tvDescripcion.text = it.productos.descripcion
                    binding.tvPrecio.text = "$ "+ it.productos.precio
                    Glide.with(requireContext()).load(it.productos.url_imagen).into(binding.imgEspecialidad)
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                }
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}