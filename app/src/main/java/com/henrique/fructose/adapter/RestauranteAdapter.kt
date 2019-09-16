package com.henrique.fructose.adapter

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.henrique.fructose.R
import com.henrique.fructose.R.string.*
import com.henrique.fructose.adapter.viewholder.RestauranteVH
import com.henrique.fructose.model.restaurant.RestaurantResponse
import com.henrique.fructose.model.restaurant.RestaurantX

class RestauranteAdapter(restauranteResponse: RestaurantResponse, private val contexto: Context,
                         private var onClick: OnRestaurantClick) : RecyclerView.Adapter<RestauranteVH>() {

    private val restauranteList = restauranteResponse.restaurants!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteVH = RestauranteVH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante,
                    parent, false))


    override fun getItemCount(): Int = restauranteList.size

    override fun onBindViewHolder(holder: RestauranteVH, position: Int) {
        val restaurant = restauranteList[position]!!
        val restaurantInfo = restaurant.restaurant!!

        isDelivery(holder, restaurantInfo)

        holder.txtAvaliacao.text = restaurantInfo.userRating!!.aggregateRating
        holder.txtNome.text = restaurantInfo.name
        holder.txtDescricao.text = restaurantInfo.phoneNumbers

        Glide.with(contexto).load(restaurantInfo.photos!![0]!!.photo!!.url).into(holder.img)
        reshapeImg(holder)

        holder.itemView.setOnClickListener { onClick.click(res = restaurant.restaurant) }
    }

    private fun isDelivery(holder: RestauranteVH, restaurantInfo: RestaurantX) {
        val situacao: String = when {
            restaurantInfo.isDeliveringNow.toString() == "1" -> contexto.getString(open)
            restaurantInfo.hasOnlineDelivery.toString() == "1" -> contexto.getString(entrega)
            else -> contexto.getString(naoentrega)
        }

        holder.txtSituacao.text = situacao
    }


    private fun reshapeImg(holder: RestauranteVH) {
        val image = holder.img
        val curveRadius = 20F

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            image.outlineProvider = object : ViewOutlineProvider() {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width,
                            (view.height + curveRadius).toInt(), curveRadius)
                }
            }
            image.clipToOutline = true
        }
    }
}