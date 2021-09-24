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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDrawerLayout()
        initProductsRecyclerView()
    }

    private fun initDrawerLayout() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding) {
            val actionBarToggle = ActionBarDrawerToggle(this@ProductsActivity, drawerLayout, 0, 0)
            actionBarToggle.syncState()

            navView.menu.findItem(R.id.nav_all_products).isChecked = true
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_all_products -> true
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
            btnNavLogin.setOnClickListener {
                startLoginActivity()
            }
            Data.currentUser?.let {
                btnNavLogin.visibility = View.GONE
                imgNavUser.visibility = View.VISIBLE
                lblNavUser.text = Data.currentUser?.username
                lblNavUser.visibility = View.VISIBLE
            }
        }
    }

    private fun initProductsRecyclerView() {
        binding.appMainContent.rvProducts.apply {
            layoutManager = GridLayoutManager(this@ProductsActivity, 2)
            adapter = ProductsAdapter(Data.products)
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
                        Toast.LENGTH_LONG
                    ).show()
                    startLoginActivity()
                } else {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivityForResult(intent, LoginActivity.LoginResult.LOGGED_IN.ordinal)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == LoginActivity.LoginResult.LOGGED_IN.ordinal) {
            initDrawerLayout()
        }
    }

}