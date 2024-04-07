package com.example.commuterx

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.reflect.KClass

private val SignUpActivityClass: KClass<LoginActivity> = LoginActivity::class

val kotlinClass: Class<SignUpActivity>
    get() = SignUpActivity.java


class LoginActivity {

}

class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    private lateinit var analytics: FirebaseAnalytics

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        analytics = FirebaseAnalytics.getInstance(this)

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Check if the email and password are correct
                if (email == "user@example.com" && password == "password123") {
                    // Login successful
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Add your login logic here, such as navigating to another activity
                } else {
                    // Login failed
                    Toast.makeText(this, "Login Failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }

            buttonSignUp.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
            // Log event for login attempt
            val bundle = Bundle()
            bundle.putString("Email", email)
            analytics.logEvent("LoginAttempt", bundle)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}