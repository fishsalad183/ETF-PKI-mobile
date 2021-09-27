package com.pki.medenjaci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pki.medenjaci.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.cart)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_cart)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = Data.currentUser?.cart?.let { cart ->
                CartAdapter(cart) { cartItem ->
                    val i = cart.indexOf(cartItem)
                    cart.removeAt(i)
                    adapter?.notifyItemRemoved(i)
                    renderOrderElements()
                }
            } ?: CartAdapter(Cart()) {}
        }
        renderOrderElements()
    }

    private fun renderOrderElements() {
        with(binding.btnCartOrder) {
            isEnabled = Data.currentUser?.cart?.size?.let { it > 0 } ?: false
            setOnClickListener { placeOrder() }
        }

        binding.lblCartTotal.text = getString(
            R.string.total_price,
            Data.currentUser?.cart?.fold(
                0,
                { total, cartItem -> total + with(cartItem) { amount * priceIncludingDiscounts } })
        )
    }

    private fun placeOrder() {
        Data.currentUser?.cart?.clear()
        Toast.makeText(this, getString(R.string.order_success), Toast.LENGTH_LONG).show()
        finish()
    }

}