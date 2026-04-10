package com.example.luby_katalog

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        animateSplash()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, 3000)
    }

    private fun animateSplash() {
        val logo = findViewById<View>(R.id.imgLogo)
        val parent = logo.parent.parent as View

        parent.alpha = 0f
        parent.translationY = 40f

        val fadeIn = ObjectAnimator.ofFloat(parent, "alpha", 0f, 1f)
        fadeIn.duration = 1000

        val slideUp = ObjectAnimator.ofFloat(parent, "translationY", 40f, 0f)
        slideUp.duration = 1000
        slideUp.interpolator = DecelerateInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeIn, slideUp)
        animatorSet.startDelay = 300
        animatorSet.start()
    }
}
