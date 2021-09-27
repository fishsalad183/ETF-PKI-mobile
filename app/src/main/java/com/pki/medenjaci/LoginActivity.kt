package com.pki.medenjaci

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.pki.medenjaci.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.login)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                Data.currentUser =
                    Data.users.find { it.username == etLoginUsername.text.toString() && it.password == etLoginPassword.text.toString() }
                Data.currentUser?.let {
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.login_successful,
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } ?: Toast.makeText(
                    this@LoginActivity,
                    R.string.login_unsuccessful_wrong_credentials,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}