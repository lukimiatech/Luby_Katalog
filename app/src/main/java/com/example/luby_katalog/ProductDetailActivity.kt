package com.example.luby_katalog

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.luby_katalog.databinding.ActivityProductDetailBinding
import com.example.luby_katalog.model.Product

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.detailRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productId = intent.getIntExtra("product_id", 1)
        val product = Product.getSampleProducts().find { it.id == productId }
            ?: Product.getSampleProducts().first()

        bindProduct(product)
        setupClickListeners()
    }

    private fun bindProduct(product: Product) {
        binding.tvProductName.text = product.name
        binding.tvPrice.text = product.price
        binding.tvSeriesBadge.text = product.series
        binding.tvDescTitle.text = product.descTitle
        binding.tvDescription.text = product.description
        binding.tvBatteryLife.text = product.batteryLife
        binding.tvLumenOutput.text = product.lumenOutput

        if (product.originalPrice != null) {
            binding.tvOriginalPrice.text = product.originalPrice
            binding.tvOriginalPrice.paintFlags =
                binding.tvOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.tvOriginalPrice.text = ""
        }

        // Spec rows
        findViewById<TextView>(R.id.tvMaterial)?.text = product.material
        findViewById<TextView>(R.id.tvChargingTime)?.text = product.chargingTime
        findViewById<TextView>(R.id.tvChargingType)?.text = product.chargingType
        findViewById<TextView>(R.id.tvWaterResistance)?.text = product.waterResistance
        findViewById<TextView>(R.id.tvBeamDistance)?.text = product.beamDistance
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnInquiry.setOnClickListener {
            Toast.makeText(this, "Inquiry terkirim! Tim kami akan menghubungi Anda.", Toast.LENGTH_SHORT).show()
        }

        binding.btnWishlist.setOnClickListener {
            Toast.makeText(this, "Ditambahkan ke wishlist!", Toast.LENGTH_SHORT).show()
        }

        binding.bottomNav.selectedItemId = R.id.nav_catalog
    }
}
