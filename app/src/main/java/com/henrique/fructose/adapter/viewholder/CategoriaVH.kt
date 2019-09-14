package com.henrique.fructose.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.henrique.fructose.R.id.imgCategoriaItem
import com.henrique.fructose.R.id.txtNomeCategoriaItem

class CategoriaVH(view: View) : ViewHolder(view) {

    val txtNome: TextView = view.findViewById(txtNomeCategoriaItem)
    val img: ImageView = view.findViewById(imgCategoriaItem)

}