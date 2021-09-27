package com.pki.medenjaci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.pki.medenjaci.databinding.ActivityUserSettingsBinding

class UserSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.account_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityUserSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fillData()
        setClickListeners()
    }

    private fun fillData() {
        Data.currentUser?.let {
            with(binding) {
                etUserSettingsFirstName.setText(it.firstName)
                etUserSettingsLastName.setText(it.lastName)
                etUserSettingsPhone.setText(it.phone)
                etUserSettingsAddress.setText(it.address)
            }
        }
    }

    private fun setClickListeners() {
        with(binding) {
            btnLogout.setOnClickListener {
                Data.currentUser = null
                finish()
                return@setOnClickListener
            }

            btnUserSettingsSaveData.setOnClickListener {
                Data.currentUser?.apply {
                    this.firstName = etUserSettingsFirstName.text.toString()
                    this.lastName = etUserSettingsLastName.text.toString()
                    this.phone = etUserSettingsPhone.text.toString()
                    this.address = etUserSettingsAddress.text.toString()
                }
                Toast.makeText(
                    this@UserSettingsActivity,
                    getString(R.string.data_change_successful),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}