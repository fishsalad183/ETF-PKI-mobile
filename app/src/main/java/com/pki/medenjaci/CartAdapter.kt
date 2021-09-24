package com.pki.medenjaci

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cart: Cart, private val renderOrderElements: () -> Unit) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val amount: TextView = itemView.findViewById(R.id.lbl_cartitem_amount)
        val image: ImageView = itemView.findViewById(R.id.img_cartitem)
        val name: TextView = itemView.findViewById(R.id.lbl_cartitem_name)
        val price: TextView = itemView.findViewById(R.id.lbl_cartitem_price)
        val actionButtonRemove: ImageView = itemView.findViewById(R.id.btn_cartitem_remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cart[position]
        with(holder) {
            amount.text = amount.context.getString(R.string.amount_number, cartItem.amount)
            image.setImageResource(cartItem.product.imgResourceID)
            name.text = cartItem.product.name
            price.text = price.context.getString(
                R.string.price_short,
                cartItem.amount * cartItem.priceIncludingDiscounts
            )
            actionButtonRemove.setOnClickListener {
                cart.removeAt(position)
                notifyItemRemoved(position)
                renderOrderElements()
            }
        }
    }

    override fun getItemCount() = cart.size

}