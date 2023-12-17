package com.alwaysondisplay.analogclock.digital.nightclock.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.R
import com.github.ybq.android.spinkit.SpinKitView

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var startTv: TextView? = null
    private var loader: SpinKitView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar!!.hide()
        startTv = findViewById(R.id.tv_start)
        loader = findViewById(R.id.spin_kit)
        Handler(Looper.getMainLooper()).postDelayed({
            startTv?.visibility = View.VISIBLE
            loader?.visibility = View.INVISIBLE
        }, 3000.toLong())
        startTv?.setOnClickListener { startMainActivity() }
    }

    private fun startMainActivity() {
        Intent(this@SplashActivity, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(this)
        }

    }
}