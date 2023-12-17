package com.alwaysondisplay.analogclock.digital.nightclock.neon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityNeonClocksBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NeonClocksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNeonClocksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNeonClocksBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.neon1.setOnClickListener { startActivity("neon1") }
        binding.neon2.setOnClickListener { startActivity("neon2") }
        binding.neon3.setOnClickListener { startActivity("neon3") }
        binding.neon4.setOnClickListener { startActivity("neon4") }
        binding.neon5.setOnClickListener { startActivity("neon5") }
        binding.neon6.setOnClickListener { startActivity("neon6") }
        binding.neon7.setOnClickListener { startActivity("neon7") }
        binding.neon8.setOnClickListener { startActivity("neon8") }
        binding.neon9.setOnClickListener { startActivity("neon9") }
        binding.neon10.setOnClickListener { startActivity("neon10") }

        val mHandler = Handler(Looper.getMainLooper())
        Thread {
            while (true) {
                try {
                    Thread.sleep(1000)
                    mHandler.post {
                        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
                        val minute = SimpleDateFormat("mm", Locale.getDefault()).format(Date())
                        val second = SimpleDateFormat("ss", Locale.getDefault()).format(Date())
                        val h = hour.toInt()
                        val m = minute.toInt()
                        val s = second.toInt()
                        binding.neon1.setTime(h, m, s)
                        binding.neon2.setTime(h, m, s)
                        binding.neon3.setTime(h, m, s)
                        binding.neon4.setTime(h, m, s)
                        binding.neon5.setTime(h, m, s)
                        binding.neon6.setTime(h, m, s)
                        binding.neon7.setTime(h, m, s)
                        binding.neon8.setTime(h, m, s)
                        binding.neon9.setTime(h, m, s)
                        binding.neon10.setTime(h, m, s)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun startActivity(data: String) {
        Intent(this@NeonClocksActivity, NeonDetailActivity::class.java).apply {
            putExtra("neon", data)
            startActivity(this)
        }
    }
}