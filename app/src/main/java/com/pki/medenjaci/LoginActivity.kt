package com.pki.medenjaci

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.et_login_username)
        val password = findViewById<EditText>(R.id.et_login_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            Data.currentUser = Data.users.find { it.username == username.text.toString() && it.password == password.text.toString() }
            if (Data.currentUser != null) {
                Toast.makeText(this, R.string.login_successful, Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, R.string.login_unsuccessful_wrong_credentials, Toast.LENGTH_SHORT).show()
            }
        }
    }
}