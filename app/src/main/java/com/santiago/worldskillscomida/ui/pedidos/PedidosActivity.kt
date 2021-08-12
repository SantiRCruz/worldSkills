package com.santiago.worldskillscomida.ui.pedidos

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.ActivityPedidosBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.bd.BdBodyProduct
import com.santiago.worldskillscomida.models.webservices.pedido.ResponsePedidos
import com.santiago.worldskillscomida.repository.local.db.DBManager
import com.santiago.worldskillscomida.ui.DialogPedido
import com.santiago.worldskillscomida.ui.MainActivity
import com.santiago.worldskillscomida.ui.PedidosAdapter
import kotlinx.coroutines.flow.collect

class PedidosActivity : AppCompatActivity() {

    private val pedidosViewModel: PedidosViewModel by viewModels()

    private lateinit var binding: ActivityPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        getPedidos()
        getTotalPedidos()
        buttonFlecha()
        buttonPedido()


    }

    private fun buttonPedido() {
        binding.buttonPedido.setOnClickListener {view->
            val dbManager = DBManager(applicationContext)
             val listPedidos = dbManager.listDataPedidos()
            val total_pedidos = dbManager.totalPrecio()

            pedidosViewModel.postPedidos(
                Constants.ID_CLIENTE,
                listPedidos.toString(),
                total_pedidos
            ).observe(this,
                Observer {
                    Log.e("pedido ",it.toString())
                    when (it) {
                        is ResponsePedidos -> {
                            if (it.respuesta == "OK") {
                                Log.e("pedido enviado",it.toString())
                                val result = dbManager.deleteAll()
                                if (result>0){
                                    val dialog = DialogPedido()
                                    dialog.show(supportFragmentManager,"pedido")


                                }else{
                                    Snackbar.make(view,"Error al enviar el pedido",Snackbar.LENGTH_LONG).show()
                                }
                            } else {
                                Snackbar.make(view,""+it.mensaje,Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                })
        }
    }

    private fun buttonFlecha() {
        binding.imgFlecha.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getTotalPedidos() {
        lifecycleScope.launchWhenStarted {
            pedidosViewModel.getTotalPedidos(applicationContext).collect {
                Log.e("prueba", it.toString())
                when (it) {
                    is Int -> {
                        binding.tvPrecio.text = " Total : $ " + it
                    }
                }
            }
        }
    }

    private fun getPedidos() {
        pedidosViewModel.getPedidos(applicationContext).observe(this, Observer {
            val list = it as List<BdBodyProduct>
            binding.rvPedidos.layoutManager = LinearLayoutManager(applicationContext)
            val adapter = PedidosAdapter(list)
            binding.rvPedidos.adapter = adapter
            binding.rvPedidos.visibility = View.VISIBLE
            if (list.size == 0) {
                binding.container.visibility = View.VISIBLE
            } else {
                binding.container.visibility = View.GONE

            }

        })
    }
}