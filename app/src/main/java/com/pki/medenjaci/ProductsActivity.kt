package com.pki.medenjaci

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.pki.medenjaci.databinding.ActivityMainBinding
import com.pki.medenjaci.databinding.NavHeaderMainBinding

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.all_products)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDrawerLayout()
        initProductsRecyclerView()
    }

    private fun initDrawerLayout() {
        with(binding) {
            val actionBarDrawerToggle = ActionBarDrawerToggle(this@ProductsActivity, drawerLayout, 0, 0)
            drawerLayout.addDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()

            navView.menu.findItem(R.id.nav_all_products).isChecked = true
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_all_products -> {
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.nav_about_us -> {
                        val intent = Intent(this@ProductsActivity, AboutUsActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }
        renderDrawerHeader()
    }

    private fun renderDrawerHeader() {
        val navViewBinding = NavHeaderMainBinding.bind(binding.navView.getHeaderView(0))
        with(navViewBinding) {
            Data.currentUser?.let {
                btnNavLogin.visibility = View.GONE
                imgNavUser.visibility = View.VISIBLE
                lblNavUser.visibility = View.VISIBLE
                lblNavUser.text = it.username
                lblNavUser.setOnClickListener {
                    val intent = Intent(this@ProductsActivity, UserSettingsActivity::class.java)
                    startActivityForResult(intent, 0)
                }
            } ?: run {
                btnNavLogin.visibility = View.VISIBLE
                imgNavUser.visibility = View.GONE
                lblNavUser.visibility = View.GONE
                lblNavUser.text = ""
                btnNavLogin.setOnClickListener {
                    val intent = Intent(this@ProductsActivity, LoginActivity::class.java)
                    startActivityForResult(intent, 0)
                }
            }
        }
    }

    private fun initProductsRecyclerView() {
        binding.appMainContent.rvProducts.apply {
            layoutManager = GridLayoutManager(this@ProductsActivity, 2)
            adapter = ProductsAdapter(Data.products) { product ->
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra("productID", product.id)
                startActivityForResult(intent, 0)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        with(binding) {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(navView)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            return true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mnu_cart -> {
                if (Data.currentUser == null) {
                    Toast.makeText(
                        this,
                        getString(R.string.must_login_before_action),
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivityForResult(intent, 0)
                } else {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        // Rerender the drawer header for when a user logs in or logs out
        renderDrawerHeader()
    }

    override fun onPause() {
        super.onPause()
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }
}