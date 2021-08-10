package com.santiago.worldskillscomida.ui.pedidos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.worldskillscomida.databinding.ActivityPedidosBinding
import com.santiago.worldskillscomida.models.bd.BdBodyProduct
import com.santiago.worldskillscomida.ui.MainActivity
import com.santiago.worldskillscomida.ui.PedidosAdapter

class PedidosActivity : AppCompatActivity() {

    private val pedidosViewModel: PedidosViewModel by viewModels()

    private lateinit var binding: ActivityPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        pedidosViewModel.getPedidos(applicationContext).observe(this, Observer {
            val list = it as List<BdBodyProduct>
            binding.rvPedidos.layoutManager = LinearLayoutManager(applicationContext)
            val adapter = PedidosAdapter(list)
            binding.rvPedidos.adapter = adapter
            binding.rvPedidos.visibility = View.VISIBLE
            if (list.size == 0){
                binding.container.visibility = View.VISIBLE
            }else{
                binding.container.visibility = View.GONE

            }

        })

        binding.imgFlecha.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}