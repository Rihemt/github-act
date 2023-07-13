package com.example.applicationactia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = username.text.toString().trim()
            val password = password.text.toString()

            if (username.isEmpty()) {
                // Champ vide
                Toast.makeText(this, "Champ vide", Toast.LENGTH_SHORT).show()
            } else if (username.contains(" ")) {

                Toast.makeText(this, "Enlever l'espace", Toast.LENGTH_SHORT).show()
            } else {
                when (validateCredentials(username, password)) {
                    AuthenticationResult.SUCCESS -> {

                        val intent = Intent(this, MainActivity2::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Authentification réussie", Toast.LENGTH_SHORT).show()
                    }
                    AuthenticationResult.INVALID_USERNAME -> {

                        Toast.makeText(this, "Identifiant incorrect", Toast.LENGTH_SHORT).show()
                    }
                    AuthenticationResult.INVALID_PASSWORD -> {

                        Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun validateCredentials(username: String, password: String): AuthenticationResult {
        // Vérifier les identifiants
        val validUsername = "rihem"
        val validPassword = "1234"

        return when {
            username != validUsername -> AuthenticationResult.INVALID_USERNAME
            password != validPassword -> AuthenticationResult.INVALID_PASSWORD
            else -> AuthenticationResult.SUCCESS
        }
    }
    enum class AuthenticationResult {
        SUCCESS,
        INVALID_USERNAME,
        INVALID_PASSWORD

    }
}


