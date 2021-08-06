package com.santiago.worldskillscomida

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.santiago.worldskillscomida.databinding.ItemCategoriaBinding
import com.santiago.worldskillscomida.models.categoriaId.Productos

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
            binding.tvItemPrecio.text = productos.precio.toString()
            Glide.with(view).load(productos.url_foto).into(binding.imgItem)
            view.setOnClickListener {
                Snackbar.make(it,"presionado ${productos.id}",Snackbar.LENGTH_LONG)
            }
        }
    }
}