package com.example.luby_katalog

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.luby_katalog.adapter.ProductAdapter
import com.example.luby_katalog.databinding.ActivityHomeBinding
import com.example.luby_katalog.model.Product

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.homeRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupProducts()
        setupBottomNav()
    }

    private fun setupProducts() {
        val products = Product.getSampleProducts()
        val adapter = ProductAdapter(products) { product ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_id", product.id)
            startActivity(intent)
        }

        binding.rvProducts.layoutManager = GridLayoutManager(this, 2)
        binding.rvProducts.adapter = adapter
    }

    private fun setupBottomNav() {
        binding.bottomNav.selectedItemId = R.id.nav_catalog

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_catalog -> true
                R.id.nav_search -> {
                    Toast.makeText(this, "Fitur pencarian akan segera hadir", Toast.LENGTH_SHORT).show()
                    false
                }
                R.id.nav_saved -> {
                    Toast.makeText(this, "Fitur favorit akan segera hadir", Toast.LENGTH_SHORT).show()
                    false
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Fitur profil akan segera hadir", Toast.LENGTH_SHORT).show()
                    false
                }
                else -> false
            }
        }
    }
}
