package com.example.shppi

object ShoppingItems {


}

data class ShopItem(
    var id: String,
    var name: String,
    var qty: Double,
    var isEdit: Boolean = false
)
