package com.addiscodr.easyshop.model

data class ProductModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val price: String = "",
    val actual_price: String = "",
    val images: List<String> = emptyList()
)
