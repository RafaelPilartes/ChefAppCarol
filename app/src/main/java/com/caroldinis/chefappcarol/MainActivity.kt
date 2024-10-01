package com.caroldinis.chefappcarol

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isLoggedIn = false // Estado de login do chef

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewMenu: ListView = findViewById(R.id.listViewMenu)
        val buttonAddNewDish: Button = findViewById(R.id.buttonAddNewDish)
        val textViewTotalItems: TextView = findViewById(R.id.textViewTotalItems)
        val buttonLogin: Button = findViewById(R.id.buttonLogin) // Novo botão de login

        // Configurar botão de login
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, LOGIN_REQUEST_CODE)
        }

        // Exibe o menu e o número total de itens
        updateMenuList(listViewMenu, textViewTotalItems)

        // Navega para a tela de adicionar novo prato
        buttonAddNewDish.setOnClickListener {
            if (isLoggedIn) {
                val intent = Intent(this, AddMenuItemActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please log in to add a dish.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Atualiza a lista de menu na tela inicial
    override fun onResume() {
        super.onResume()
        val listViewMenu: ListView = findViewById(R.id.listViewMenu)
        val textViewTotalItems: TextView = findViewById(R.id.textViewTotalItems)
        updateMenuList(listViewMenu, textViewTotalItems)
    }

    // Atualiza a lista de menu e o total de itens
    private fun updateMenuList(listView: ListView, textViewTotalItems: TextView) {
        val menu = MenuData.getMenu()
        val menuStrings = menu.map { "${it.name} - ${it.course}: \$${it.price}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuStrings)
        listView.adapter = adapter

        // Exibe o número total de itens no menu
        val totalItems = menu.size
        textViewTotalItems.text = "Total items: $totalItems"
    }

    // Recebe o resultado do LoginActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            isLoggedIn = true
            // Atualiza a visibilidade do botão "Add New Dish"
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
