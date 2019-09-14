package com.henrique.fructose.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.henrique.fructose.R
import com.henrique.fructose.adapter.viewholder.CategoriaVH
import com.henrique.fructose.model.category.Category

class CategoriaAdapter(listaCategoria:List<Category>, val contexto: Context, var onClick: OnClick, var onPicture: OnPicture)
    : RecyclerView.Adapter<CategoriaVH>() {

    private val categoryList = listaCategoria

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaVH = CategoriaVH(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria, parent, false))


    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoriaVH, position: Int) {
        //Toast.makeText(contexto, "Adapter", Toast.LENGTH_SHORT).show()

        val category = categoryList[position]

        holder.txtNome.text = category.categories.name

        onPicture.getPic(category.categories.name, position, holder.img)

        holder.itemView.setOnClickListener { onClick.click() }
    }

}

interface OnClick{
    fun click()
}

interface OnPicture{
    fun getPic(name:String, posticao:Int, imgView:ImageView)
}

