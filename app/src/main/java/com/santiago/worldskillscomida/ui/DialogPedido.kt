package com.santiago.worldskillscomida.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.DialogPedidoBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.ui.pedidos.PedidosActivity

class DialogPedido:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.dialog_pedido,container,false)

        val binding = DialogPedidoBinding.bind(rootView)
        binding.imgClose.setOnClickListener {
            val intent = Intent(requireContext(),PedidosActivity::class.java)
            startActivity(intent)
        }
        binding.ButtonOk.setOnClickListener {
            val intent = Intent(requireContext(),PedidosActivity::class.java)
            startActivity(intent)
        }
        isCancelable = false
        return binding.root
    }

}