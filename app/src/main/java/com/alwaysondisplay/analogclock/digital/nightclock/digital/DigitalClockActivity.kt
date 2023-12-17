package com.alwaysondisplay.analogclock.digital.nightclock.digital

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityDigitalClockBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DigitalClockActivity : AppCompatActivity() {

    val mHandler = Handler(Looper.getMainLooper())

    private lateinit var binding: ActivityDigitalClockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDigitalClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clock1Layout.setOnClickListener {
            startActivity("digital1")
        }
        binding.clock2Layout.setOnClickListener {
            startActivity("digital2")
        }
        binding.clock3Layout.setOnClickListener {
            startActivity("digital3")
        }
        binding.clock4Layout.setOnClickListener {
            startActivity("digital4")
        }
        binding.clock5Layout.setOnClickListener {
            startActivity("digital5")
        }
        binding.clock6Layout.setOnClickListener {
            startActivity("digital6")
        }
        binding.clock7Layout.setOnClickListener {
            startActivity("digital7")
        }
        binding.clock8Layout.setOnClickListener {
            startActivity("digital8")
        }

        val sec = binding.sec
        val min = binding.cpvSecond3
        val sec1 = binding.SecProgressBar
        val min1 = binding.MinProgressBar
        val min2 = binding.cpvMinute6
        val sec3 = binding.cpvSecond6
        val sec4 = binding.cpvSecond7

        Thread {
            while (true) {
                try {
                    Thread.sleep(1000)
                    mHandler.post {
                        val seconds = SimpleDateFormat("ss", Locale.getDefault()).format(Date())
                        val minute = SimpleDateFormat("mm", Locale.getDefault()).format(Date())
                        sec.progress = seconds.toFloat()
                        sec1.progress = seconds.toFloat()
                        min.setValue(minute.toFloat())
                        min1.progress = minute.toFloat()
                        min2.setValue(minute.toFloat())
                        sec3.setValue(seconds.toFloat())
                        sec4.setValue(seconds.toFloat())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun startActivity(data: String) {
        Intent(this@DigitalClockActivity, DigitalClockPreviewActivity::class.java).apply {
            putExtra("digital", data)
            startActivity(this)
        }
    }
}