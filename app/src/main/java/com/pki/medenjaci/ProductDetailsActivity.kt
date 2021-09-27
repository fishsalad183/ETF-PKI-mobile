package com.pki.medenjaci

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.pki.medenjaci.databinding.ActivityProductBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.product_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initElements()
    }

    private fun initElements() {
        val productID = intent.getIntExtra("productID", -1)
        val product: Product = Data.products.find { it.id == productID } ?: run {
            Toast.makeText(this, getString(R.string.an_error_occurred), Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        with(binding) {
            lblProductdetailsTitle.text = product.name
            lblProductdetailsDescription.text = product.description
            imgProductdetails.setImageResource(product.imgResourceID)

            lblProductdetailsPrice.text = if (product.discountPrice != null) {
                val str = SpannableString(
                    getString(
                        R.string.price_discount_long,
                        product.price,
                        product.discountPrice
                    )
                )
                str.setSpan(
                    StrikethroughSpan(),
                    6,
                    str.indexOf(" ", str.indexOf(" ") + 1),
                    SpannableString.SPAN_INCLUSIVE_EXCLUSIVE
                )
                str
            } else {
                getString(R.string.price_long, product.price)
            }

            spnProductdetailsAmount.adapter = ArrayAdapter<Int>(
                this@ProductDetailsActivity,
                R.layout.support_simple_spinner_dropdown_item,
                arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            )

            btnProductdetailsAddtocart.setOnClickListener {
                Data.currentUser?.let {
                    val amount = spnProductdetailsAmount.selectedItem as Int
                    Data.currentUser?.cart?.add(CartItem(product, amount))
                    Toast.makeText(
                        this@ProductDetailsActivity,
                        getString(R.string.added_to_cart, amount, product.name),
                        Toast.LENGTH_LONG
                    ).show()
                    finish()
                } ?: run {
                    Toast.makeText(
                        this@ProductDetailsActivity,
                        getString(R.string.must_login_before_action),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@ProductDetailsActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

}