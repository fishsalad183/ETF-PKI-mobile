package com.pki.medenjaci

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pki.medenjaci.databinding.ItemProductsBinding

class ProductsAdapter(
    private val products: MutableList<Product>,
    private val productClickListener: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemProductsBinding: ItemProductsBinding) :
        RecyclerView.ViewHolder(itemProductsBinding.root) {

        fun bind(product: Product) {
            itemProductsBinding.apply {
                lblProductsItemName.text = product.name
                imgProductsItem.setImageResource(product.imgResourceID)

                product.discountPrice?.let {
                    lblProductsItemPrice.text = lblProductsItemPrice.context.getString(
                        R.string.price_short,
                        product.discountPrice
                    )
                    lblProductsItemPrice.setTextColor(
                        lblProductsItemPrice.context.resources.getColor(
                            R.color.discount_price_color
                        )
                    )
                } ?: run {
                    lblProductsItemPrice.text =
                        lblProductsItemPrice.context.getString(R.string.price_short, product.price)
                }

                itemProductsBinding.root.setOnClickListener { productClickListener(product) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount() = products.size

}