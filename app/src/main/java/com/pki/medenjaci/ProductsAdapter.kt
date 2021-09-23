package com.pki.medenjaci

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter(private val products: MutableList<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.lbl_products_item_name)
        val price: TextView = itemView.findViewById(R.id.lbl_products_item_price)
        val image: ImageView = itemView.findViewById(R.id.img_products_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetailsActivity::class.java)
            intent.putExtra("productID", viewType)
            parent.context.startActivity(intent)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.name
        holder.image.setImageResource(product.imgResourceID)

        val context = holder.price.context
        if (product.discountPrice != null) {
            holder.price.text = context.getString(R.string.price_short, product.discountPrice)
            holder.price.setTextColor(context.resources.getColor(R.color.discount_price_color))
        } else {
            holder.price.text = context.getString(R.string.price_short, product.price)
        }
    }

    override fun getItemCount() = products.size

    override fun getItemViewType(position: Int): Int {
        return products[position].id
    }
}