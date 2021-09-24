package com.pki.medenjaci

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class ProductsActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.all_products)

        initDrawerLayout()
        initProductsRecyclerView()
    }

    private fun initDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
//        drawerLayout.addDrawerListener(DrawerRenderer(actionBarToggle))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()

        navView = findViewById(R.id.nav_view)
        navView.menu.findItem(R.id.nav_all_products).isChecked = true
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all_products -> {

                    true
                }
                R.id.nav_about_us -> {
                    val intent = Intent(this, AboutUsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        renderDrawerHeader()
    }

    private fun renderDrawerHeader() {
        val btnLogin = navView.getHeaderView(0).findViewById<Button>(R.id.btn_nav_login)
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        if (Data.currentUser != null) {
            btnLogin.visibility = View.GONE
            val userIcon = navView.getHeaderView(0).findViewById<ImageView>(R.id.img_nav_user)
            userIcon.visibility = View.VISIBLE
            val username = navView.getHeaderView(0).findViewById<TextView>(R.id.lbl_nav_user)
            username.text = Data?.currentUser?.username
            username.visibility = View.VISIBLE
        }
    }

//    inner class DrawerRenderer(private val actionBarDrawerToggle: ActionBarDrawerToggle) :
//        DrawerLayout.DrawerListener {
//        override fun onDrawerSlide(drawerView: View, slideOffset: Float) =
//            actionBarDrawerToggle.onDrawerSlide(drawerView, slideOffset)
//
//        override fun onDrawerOpened(drawerView: View) {
//            renderDrawerHeader()
//            actionBarDrawerToggle.onDrawerOpened(drawerView)
//        }
//
//        override fun onDrawerClosed(drawerView: View) {
//            renderDrawerHeader()
//            actionBarDrawerToggle.onDrawerClosed(drawerView)
//        }
//
//        override fun onDrawerStateChanged(newState: Int) {
//            renderDrawerHeader()
//            actionBarDrawerToggle.onDrawerStateChanged(newState)
//        }
//    }

    private fun initProductsRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_products)
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@ProductsActivity, 2)
            adapter = ProductsAdapter(Data.products)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.openDrawer(navView)
        } else {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        return true
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
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}