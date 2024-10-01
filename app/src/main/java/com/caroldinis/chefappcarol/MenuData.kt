package com.caroldinis.chefappcarol

object MenuData {
    private val menuList = mutableListOf<MenuItem>()

    fun addDish(dish: MenuItem) {
        menuList.add(dish)
    }

    fun getMenu(): List<MenuItem> {
        return menuList
    }
}

data class MenuItem(
    val name: String,
    val description: String,
    val course: String,
    val price: Double
)