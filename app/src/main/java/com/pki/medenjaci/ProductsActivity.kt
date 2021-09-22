package com.pki.medenjaci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.adtivity_products)

        val recyclerView = findViewById<RecyclerView>(R.id.products_recycler_view)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@ProductsActivity, 2)
            adapter = ProductsAdapter(Data.products)
        }
    }

}