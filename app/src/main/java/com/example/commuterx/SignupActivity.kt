package com.example.commuterx

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.analytics.FirebaseAnalytics

class SignUpActivity : AppCompatActivity() {

    private lateinit var fullNameInputLayout: TextInputLayout
    private lateinit var emailInputLayout: TextInputLayout
    private lateinit var dateOfBirthInputLayout: TextInputLayout
    private lateinit var passwordInputLayout: TextInputLayout
    private lateinit var signUpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullNameInputLayout = findViewById(R.id.fullNameInputLayout)
        emailInputLayout = findViewById(R.id.emailInputLayout)
        dateOfBirthInputLayout = findViewById(R.id.dateOfBirthInputLayout)
        passwordInputLayout = findViewById(R.id.passwordInputLayout)
        signUpButton = findViewById(R.id.signUpButton)

        signUpButton.setOnClickListener {
            val fullName = fullNameInputLayout.editText?.text.toString()
            val email = emailInputLayout.editText?.text.toString()
            val dateOfBirth = dateOfBirthInputLayout.editText?.text.toString()
            val password = passwordInputLayout.editText?.text.toString()

            if (validateInput(fullName, email, dateOfBirth, password)) {
                // Add your logic here to create a new user account or perform other actions

                // Log a sign-up event in Firebase Analytics
                val bundle = Bundle()
                bundle.putString("full_name", fullName)
                bundle.putString("email", email)
                bundle.putString("date_of_birth", dateOfBirth)
                FirebaseAnalytics.getInstance(this).logEvent("sign_up", bundle)
            }
        }
    }

    private fun validateInput(fullName: String, email: String, dateOfBirth: String, password: String): Boolean {
        if (fullName.isEmpty()) {
            fullNameInputLayout.error = "Full name is required"
            return false
        }
        if (email.isEmpty()) {
            emailInputLayout.error = "Email is required"
            return false
        }
        if (dateOfBirth.isEmpty()) {
            dateOfBirthInputLayout.error = "Date of birth is required"
            return false
        }
        if (password.length < 8) {
            passwordInputLayout.error = "Password must be at least 8 characters long"
            return false
        }
        return true
    }

    companion object {
        val java: Class<SignUpActivity> = SignUpActivity::class.java
    }

    // ...
}
