package com.example.luby_katalog

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
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
            val referralCode = binding.etReferralCode.text.toString().trim()

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
                    showConfirmationDialog(
                        name = name,
                        email = email,
                        phone = phone,
                        nik = nik,
                        password = password,
                        referralCode = referralCode
                    )
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun showConfirmationDialog(
        name: String,
        email: String,
        phone: String,
        nik: String,
        password: String,
        referralCode: String
    ) {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.dialog_register_confirmation, null, false)

        dialogView.findViewById<TextView>(R.id.tvConfirmName).text =
            getString(R.string.confirm_name_format, name)
        dialogView.findViewById<TextView>(R.id.tvConfirmEmail).text =
            getString(R.string.confirm_email_format, email)
        dialogView.findViewById<TextView>(R.id.tvConfirmPhone).text =
            getString(R.string.confirm_phone_format, phone)
        dialogView.findViewById<TextView>(R.id.tvConfirmNik).text =
            getString(R.string.confirm_nik_format, nik)
        dialogView.findViewById<TextView>(R.id.tvConfirmPassword).text =
            getString(R.string.confirm_password_format, maskPassword(password))
        dialogView.findViewById<TextView>(R.id.tvConfirmKtp).text =
            getString(R.string.confirm_ktp_format, getString(R.string.confirm_ktp_ready))
        dialogView.findViewById<TextView>(R.id.tvConfirmReferral).text =
            getString(
                R.string.confirm_referral_format,
                referralCode.ifEmpty { getString(R.string.confirm_referral_empty) }
            )

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.findViewById<TextView>(R.id.btnDialogEdit).setOnClickListener {
            dialog.dismiss()
        }

        dialogView.findViewById<TextView>(R.id.btnDialogSubmit).setOnClickListener {
            dialog.dismiss()
            Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
            finish()
        }

        dialog.show()
    }

    private fun maskPassword(password: String): String {
        return "•".repeat(password.length.coerceAtLeast(6))
    }
}
