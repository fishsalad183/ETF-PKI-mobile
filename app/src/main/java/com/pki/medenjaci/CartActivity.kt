package com.pki.medenjaci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity(), Renderer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        supportActionBar?.title = getString(R.string.cart)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_cart)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = CartAdapter(Data.currentUser?.cart ?: Cart(), this@CartActivity)
        }
        renderOrderElements()
    }

    override fun renderOrderElements() {
        val btnOrder = findViewById<Button>(R.id.btn_cart_order)
        btnOrder.isEnabled = Data.currentUser?.cart?.size?.let { it > 0 } ?: false
        btnOrder.setOnClickListener { placeOrder() }

        val totalPrice = findViewById<TextView>(R.id.lbl_cart_total)
        totalPrice.text = getString(R.string.total_price, Data?.currentUser?.cart?.fold(0, { total, cartItem -> total + with (cartItem) { amount * priceIncludingDiscounts } }))
    }

    private fun placeOrder() {
        Data.currentUser?.cart?.clear()
        Toast.makeText(this, getString(R.string.order_success), Toast.LENGTH_LONG).show()
        finish()
    }

}

interface Renderer {
    fun renderOrderElements()
}