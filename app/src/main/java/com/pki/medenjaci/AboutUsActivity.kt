package com.pki.medenjaci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        supportActionBar?.title = getString(R.string.about_us)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}