package com.santiago.worldskillscomida.ui.detalle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.FragmentDetalleBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.bd.BdBodyProduct
import com.santiago.worldskillscomida.models.webservices.producto.ResponseProducto
import com.santiago.worldskillscomida.repository.local.db.DBManager
import com.santiago.worldskillscomida.ui.pedidos.PedidosActivity


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

        detalleViewModel.getProductoId(Constants.ID_PRODUCTO).observe(viewLifecycleOwner, Observer {data->
            when(data){
                is ResponseProducto->{
                    binding.tvNombre.text = data.productos.nombre
                    binding.tvDescripcion.text = data.productos.descripcion
                    binding.tvPrecio.text = "$ "+ data.productos.precio
                    Glide.with(requireContext()).load(data.productos.url_imagen).into(binding.imgEspecialidad)
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                    binding.buttonAgregar.setOnClickListener {
                        val dbManager = DBManager(requireContext())
                        var precio_iva = (data.productos.precio*0.19)+data.productos.precio
                        Log.e("data",BdBodyProduct(0,data.productos.id,data.productos.nombre,data.productos.descripcion,data.productos.url_imagen,precio_iva.toInt(),precio_iva.toInt(),1).toString())
                        dbManager.insertData(BdBodyProduct(0,data.productos.id,data.productos.nombre,data.productos.descripcion,data.productos.url_imagen,precio_iva.toInt(),precio_iva.toInt(),1))
                        val intent = Intent(activity,PedidosActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}