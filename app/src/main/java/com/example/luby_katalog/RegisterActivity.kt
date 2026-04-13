package com.example.luby_katalog

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.luby_katalog.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var ktpImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            ktpImageUri = uri
            binding.imgKtpPreview.setImageURI(uri)
            binding.frameKtpPreview.visibility = View.VISIBLE
            binding.tvUploadLabel.text = getString(R.string.btn_change_ktp)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.registerRoot) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnUploadKtp.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnRegister.setOnClickListener {
            val name = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etPhone.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()
            val nik = binding.etNik.text.toString().trim()

            when {
                name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || nik.isEmpty() -> {
                    Toast.makeText(this, "Mohon lengkapi semua field wajib", Toast.LENGTH_SHORT).show()
                }
                nik.length != 16 -> {
                    Toast.makeText(this, "NIK harus 16 digit", Toast.LENGTH_SHORT).show()
                }
                ktpImageUri == null -> {
                    Toast.makeText(this, "Mohon upload foto KTP Anda", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(this, "Password tidak cocok", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }
}
