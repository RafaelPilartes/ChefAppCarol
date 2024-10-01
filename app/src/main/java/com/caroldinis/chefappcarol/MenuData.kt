package com.caroldinis.chefappcarol

object MenuData {
    private val menuList = mutableListOf<MenuItem>()

    init {
        // Add sample dishes
        menuList.add(MenuItem("Bruschetta", "Toasted bread with tomatoes", "Starter", 5.0))
        menuList.add(MenuItem("Grilled Chicken", "Juicy grilled chicken with herbs", "Main Course", 12.0))
        menuList.add(MenuItem("Cheesecake", "Rich cheesecake with strawberry sauce", "Dessert", 6.0))
    }

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