package com.henrique.fructose.adapter

import com.henrique.fructose.model.restaurant.Restaurant
import com.henrique.fructose.model.restaurant.RestaurantX

interface OnClick{
    fun click()
}
interface OnRestaurantClick{
    fun click(res: RestaurantX)
}
