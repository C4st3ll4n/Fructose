package com.henrique.fructose.adapter

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.henrique.fructose.R
import com.henrique.fructose.adapter.viewholder.CategoriaVH
import com.henrique.fructose.model.category.Category
import com.henrique.fructose.model.restaurant.RestaurantX

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

        onPicture.getPic(category.categories.name,holder.img)

        holder.itemView.setOnClickListener { onClick.click() }

        val image = holder.img
        val curveRadius = 20F

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            image.outlineProvider = object : ViewOutlineProvider() {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width,
                            (view.height+curveRadius).toInt(), curveRadius)
                }
            }

            image.clipToOutline = true

        }
    }

}

