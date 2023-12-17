package com.alwaysondisplay.analogclock.digital.nightclock.abstract

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivitySelectAbstractClockBinding
import com.leondzn.simpleanalogclock.SimpleAnalogClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SelectAbstractClockActivity : AppCompatActivity() {

    private lateinit var abstract1: SimpleAnalogClock
    private lateinit var abstract2: SimpleAnalogClock
    private lateinit var abstract3: SimpleAnalogClock
    private lateinit var abstract4: SimpleAnalogClock
    private lateinit var abstract5: SimpleAnalogClock
    private lateinit var abstract6: SimpleAnalogClock
    private lateinit var abstract7: SimpleAnalogClock
    private lateinit var abstract8: SimpleAnalogClock
    private lateinit var abstract9: SimpleAnalogClock
    private lateinit var abstract10: SimpleAnalogClock
    private lateinit var binding: ActivitySelectAbstractClockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectAbstractClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        abstract1 = binding.abstract1
        abstract2 = binding.abstract2
        abstract3 = binding.abstract3
        abstract4 = binding.abstract4
        abstract5 = binding.abstract5
        abstract6 = binding.abstract6
        abstract7 = binding.abstract7
        abstract8 = binding.abstract8
        abstract9 = binding.abstract9
        abstract10 = binding.abstract10

        abstract1.setOnClickListener { startActivity("abstract1") }
        abstract2.setOnClickListener { startActivity("abstract2") }
        abstract3.setOnClickListener { startActivity("abstract3") }
        abstract4.setOnClickListener { startActivity("abstract4") }
        abstract5.setOnClickListener { startActivity("abstract5") }
        abstract6.setOnClickListener { startActivity("abstract6") }
        abstract7.setOnClickListener { startActivity("abstract7") }
        abstract8.setOnClickListener { startActivity("abstract8") }
        abstract9.setOnClickListener { startActivity("abstract9") }
        abstract10.setOnClickListener { startActivity("abstract10") }

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
                        abstract1.setTime(h, m, s)
                        abstract2.setTime(h, m, s)
                        abstract3.setTime(h, m, s)
                        abstract4.setTime(h, m, s)
                        abstract5.setTime(h, m, s)
                        abstract6.setTime(h, m, s)
                        abstract7.setTime(h, m, s)
                        abstract8.setTime(h, m, s)
                        abstract9.setTime(h, m, s)
                        abstract10.setTime(h, m, s)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun startActivity(data: String) {
        Intent(this@SelectAbstractClockActivity, AbstractClockDetailsActivity::class.java).apply {
            putExtra("abstract", data)
            startActivity(this)
        }
    }
}