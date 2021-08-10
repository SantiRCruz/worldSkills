package com.santiago.worldskillscomida.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.ItemPedidosBinding
import com.santiago.worldskillscomida.models.bd.BdBodyProduct
import com.santiago.worldskillscomida.repository.local.db.DBManager
import com.santiago.worldskillscomida.ui.pedidos.PedidosActivity

class PedidosAdapter(val bdBodyProduct: List<BdBodyProduct>) :
    RecyclerView.Adapter<PedidosAdapter.PedidosHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PedidosHolder(
            layoutInflater.inflate(R.layout.item_pedidos, parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: PedidosHolder, position: Int) {
        holder.bind(bdBodyProduct[position])
    }

    override fun getItemCount(): Int = bdBodyProduct.size

    class PedidosHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {

        private var binding = ItemPedidosBinding.bind(view)
        fun bind(bdBodyProduct: BdBodyProduct) {
            val dbManager = DBManager(context)
            binding.tvItemNombre.text = bdBodyProduct.nombre
            binding.tvItemCantidad.text = bdBodyProduct.cantidad.toString()
            binding.tvItemPrecio.text = "$ " + bdBodyProduct.precio_iva_total.toString()
            Glide.with(view).load(bdBodyProduct.url_imagen).into(binding.imgItem)
            if (bdBodyProduct.cantidad == 1)binding.buttonRestar.setImageResource(R.drawable.ic_baseline_delete_forever_24)
            binding.buttonRestar.setOnClickListener {
                if (bdBodyProduct.cantidad == 1) {
                    val result = dbManager.deleteId(bdBodyProduct.id)
                    if (result > 0) {
                        val intent = Intent(context, PedidosActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                }else{
                    dbManager.updateCantidad(bdBodyProduct.id,(bdBodyProduct.precio_iva_unidad*(bdBodyProduct.cantidad-1)),(bdBodyProduct.cantidad-1))
                    val intent = Intent(context, PedidosActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
            }
            binding.buttonSumar.setOnClickListener {
                dbManager.updateCantidad(bdBodyProduct.id,bdBodyProduct.precio_iva_unidad*(bdBodyProduct.cantidad+1),bdBodyProduct.cantidad+1)
                val intent = Intent(context, PedidosActivity::class.java)
                ContextCompat.startActivity(context, intent, null)

            }
        }
    }
}