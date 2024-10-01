package com.caroldinis.chefappcarol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*



class AddMenuItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_menu_item)

        // Referenciando os elementos da UI
        val editTextDishName: EditText = findViewById(R.id.editTextDishName)
        val editTextDishDescription: EditText = findViewById(R.id.editTextDishDescription)
        val spinnerCourse: Spinner = findViewById(R.id.spinnerCourse)
        val editTextDishPrice: EditText = findViewById(R.id.editTextDishPrice)
        val buttonAddDish: Button = findViewById(R.id.buttonAddDish)

        // Definindo as opções de curso no spinner
        val courses = arrayOf("Starter", "Main Course", "Dessert")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourse.adapter = adapter

        // Lógica ao clicar no botão "Add Dish"
        buttonAddDish.setOnClickListener {
            val dishName = editTextDishName.text.toString()
            val dishDescription = editTextDishDescription.text.toString()
            val course = spinnerCourse.selectedItem.toString()
            val dishPrice = editTextDishPrice.text.toString().toDoubleOrNull()

            // Validação simples
            if (dishName.isNotEmpty() && dishDescription.isNotEmpty() && dishPrice != null) {
                // Salvando o prato no menu (usando uma lista temporária ou banco de dados)
                MenuData.addDish(MenuItem(dishName, dishDescription, course, dishPrice))

                // Exibindo uma mensagem de confirmação
                Toast.makeText(this, "$dishName added to menu!", Toast.LENGTH_SHORT).show()

                // Limpar os campos após adicionar
                editTextDishName.text.clear()
                editTextDishDescription.text.clear()
                editTextDishPrice.text.clear()
            } else {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}