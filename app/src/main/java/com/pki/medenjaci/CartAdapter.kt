package com.pki.medenjaci

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pki.medenjaci.databinding.ItemCartBinding
import com.pki.medenjaci.databinding.ItemProductsBinding

class CartAdapter(private val cart: Cart, private val cartItemClickListener: (CartItem) -> Unit) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemCartBinding: ItemCartBinding) : RecyclerView.ViewHolder(itemCartBinding.root) {

        fun bind(cartItem: CartItem) {
            itemCartBinding.apply {
                lblCartitemAmount.text = lblCartitemAmount.context.getString(R.string.amount_number, cartItem.amount)
                imgCartitem.setImageResource(cartItem.product.imgResourceID)
                lblCartitemName.text = cartItem.product.name
                lblCartitemPrice.text = lblCartitemPrice.context.getString(R.string.price_short, with (cartItem) { amount * priceIncludingDiscounts })
            }
            itemCartBinding.btnCartitemRemove.setOnClickListener { cartItemClickListener(cartItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount() = cart.size

}