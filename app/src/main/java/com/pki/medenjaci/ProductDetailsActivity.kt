package com.pki.medenjaci

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.title = getString(R.string.product_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initElements()
    }

    private fun initElements() {
        val productID = intent.getIntExtra("productID", -1)
        val product = Data.products.find { it.id == productID } ?: TODO()

        val title = findViewById<TextView>(R.id.lbl_productdetails_title)
        title.text = product.name
        val description = findViewById<TextView>(R.id.lbl_productdetails_description)
        description.text = product.description
        val img = findViewById<ImageView>(R.id.img_productdetails)
        img.setImageResource(product.imgResourceID)

        val price = findViewById<TextView>(R.id.lbl_productdetails_price)
        if (product.discountPrice != null) {
            val str = SpannableString(getString(R.string.price_discount_long, product.price, product.discountPrice))
            str.setSpan(StrikethroughSpan(), 6, str.indexOf(" ", str.indexOf(" ") + 1), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
            price.text = str
        } else {
            price.text = getString(R.string.price_long, product.price)
        }

        val spnAmount = findViewById<Spinner>(R.id.spn_productdetails_amount)
        spnAmount.adapter = ArrayAdapter<Int>(this, R.layout.support_simple_spinner_dropdown_item, arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

        val btnAddToCart= findViewById<Button>(R.id.btn_productdetails_addtocart)
        btnAddToCart.setOnClickListener {
            if (Data.currentUser == null) {
                Toast.makeText(this, getString(R.string.must_login_before_action), Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val amount = spnAmount.selectedItem as Int
                Data.currentUser?.cart?.add(CartItem(product, amount))
                Toast.makeText(this, getString(R.string.added_to_cart, amount, product.name), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

}