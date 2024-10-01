package com.caroldinis.chefappcarol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*



class AddMenuItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu_item)

        // Referencing UI Elements
        val editTextDishName: EditText = findViewById(R.id.editTextDishName)
        val editTextDishDescription: EditText = findViewById(R.id.editTextDishDescription)
        val spinnerCourse: Spinner = findViewById(R.id.spinnerCourse)
        val editTextDishPrice: EditText = findViewById(R.id.editTextDishPrice)
        val buttonAddDish: Button = findViewById(R.id.buttonAddDish)

        // Setting course options in the spinner
        val courses = arrayOf("Starter", "Main Course", "Dessert")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = adapter

        // Logic when clicking the "Add Dish" button
        buttonAddDish.setOnClickListener {
            val dishName = editTextDishName.text.toString()
            val dishDescription = editTextDishDescription.text.toString()
            val course = spinnerCourse.selectedItem.toString()
            val dishPrice = editTextDishPrice.text.toString().toDoubleOrNull()

            // Simple validation
            if (dishName.isNotEmpty() && dishDescription.isNotEmpty() && dishPrice != null) {
                // Saving the dish to the menu (using a temporary list or database)
                MenuData.addDish(MenuItem(dishName, dishDescription, course, dishPrice))

                // Displaying a confirmation message
                Toast.makeText(this, "$dishName added to menu!", Toast.LENGTH_SHORT).show()

                // Clearing fields after adding
                editTextDishName.text.clear()
                editTextDishDescription.text.clear()
                editTextDishPrice.text.clear()
            } else {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}