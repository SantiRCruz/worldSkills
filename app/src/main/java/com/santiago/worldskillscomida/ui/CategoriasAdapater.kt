package com.santiago.worldskillscomida.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.worldskillscomida.R
import com.santiago.worldskillscomida.databinding.ItemCategoriaBinding
import com.santiago.worldskillscomida.models.Constants
import com.santiago.worldskillscomida.models.webservices.categoriaId.Productos

class CategoriasAdapater(val productos :List<Productos>):RecyclerView.Adapter<CategoriasAdapater.CategoriasHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriasHolder(layoutInflater.inflate(R.layout.item_categoria,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: CategoriasHolder, position: Int) {
        holder.bind(productos[position])
    }

    override fun getItemCount(): Int {
        return productos.size
    }

    class CategoriasHolder(val view : View,val context:Context):RecyclerView.ViewHolder(view){
        private var binding = ItemCategoriaBinding.bind(view )
        fun bind(productos: Productos){
            binding.tvItemNombre.text = productos.nombre
            binding.tvItemDescripcion.text = productos.descripcion
            binding.tvItemPrecio.text = "$ " + productos.precio
            Glide.with(view).load(productos.url_imagen).into(binding.imgItem)
            binding.root.setOnClickListener {
                Constants.ID_PRODUCTO = productos.id
                Navigation.findNavController(it).navigate(R.id.navigation_detalle)
            }
        }
    }
}