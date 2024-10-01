package com.caroldinis.chefappcarol

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isLoggedIn = false // Chef Login State

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewMenu: ListView = findViewById(R.id.listViewMenu)
        val buttonAddNewDish: Button = findViewById(R.id.buttonAddNewDish)
        val textViewTotalItems: TextView = findViewById(R.id.textViewTotalItems)
        val buttonLogin: Button = findViewById(R.id.buttonLogin) // New Login Button

        // Configure Login Button
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, LOGIN_REQUEST_CODE)
        }

        // Displays the menu and total number of items
        updateMenuList(listViewMenu, textViewTotalItems)

        // Navigates to the Add New Dish screen
        buttonAddNewDish.setOnClickListener {
            if (isLoggedIn) {
                val intent = Intent(this, AddMenuItemActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please log in to add a dish.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Refreshes the menu list on the home screen
    override fun onResume() {
        super.onResume()
        val listViewMenu: ListView = findViewById(R.id.listViewMenu)
        val textViewTotalItems: TextView = findViewById(R.id.textViewTotalItems)
        updateMenuList(listViewMenu, textViewTotalItems)
    }

    // Refreshes the menu list and total number of items
    private fun updateMenuList(listView: ListView, textViewTotalItems: TextView) {
        val menu = MenuData.getMenu()
        val menuStrings = menu.map { "${it.name} - ${it.course}: R${it.price}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuStrings)
        listView.adapter = adapter

        // Displays the total number of items on the menu
        val totalItems = menu.size
        textViewTotalItems.text = "Total items: $totalItems"
    }

    // Gets the result of the LoginActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            isLoggedIn = true
            // Updates the visibility of the "Add New Dish" button
            val buttonAddNewDish: Button = findViewById(R.id.buttonAddNewDish)
            val buttonLogin: Button = findViewById(R.id.buttonLogin)
            buttonAddNewDish.visibility = Button.VISIBLE
            buttonLogin.visibility = Button.GONE
        }
    }

    companion object {
        private const val LOGIN_REQUEST_CODE = 100
    }
}
