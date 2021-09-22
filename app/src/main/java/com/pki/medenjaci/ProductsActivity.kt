package com.pki.medenjaci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_products)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@ProductsActivity, 2)
            adapter = ProductsAdapter(Data.products)
        }
    }

}