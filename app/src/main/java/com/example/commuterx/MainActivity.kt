package com.example.commuterx

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (username == "your_username" && password == "your_password") {
                // Successful login
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                // Add your login logic here, such as navigating to another activity
            } else {
                // Failed login
                Toast.makeText(this, "Login Failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}