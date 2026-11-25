package com.addiscodr.easyshop.model

data class UserModel(
    val name: String = "",
    val email: String = "",
    val uid: String = "",
    val cart_items: Map<String, Long> = emptyMap(),
    val address: String = "",
)
