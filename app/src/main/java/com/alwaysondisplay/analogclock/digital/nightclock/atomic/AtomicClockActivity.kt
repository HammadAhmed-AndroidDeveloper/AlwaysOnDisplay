package com.alwaysondisplay.analogclock.digital.nightclock.atomic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityAtomicClockBinding
import com.leondzn.simpleanalogclock.SimpleAnalogClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AtomicClockActivity : AppCompatActivity() {

    private lateinit var atomic1: SimpleAnalogClock
    private lateinit var atomic2: SimpleAnalogClock
    private lateinit var atomic3: SimpleAnalogClock
    private lateinit var atomic4: SimpleAnalogClock
    private lateinit var atomic5: SimpleAnalogClock
    private lateinit var atomic6: SimpleAnalogClock
    private lateinit var atomic7: SimpleAnalogClock
    private lateinit var atomic8: SimpleAnalogClock
    private lateinit var atomic9: SimpleAnalogClock
    private lateinit var atomic10: SimpleAnalogClock
    private lateinit var binding: ActivityAtomicClockBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtomicClockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        atomic1 = binding.atomic1
        atomic2 = binding.atomic2
        atomic3 = binding.atomic3
        atomic4 = binding.atomic4
        atomic5 = binding.atomic5
        atomic6 = binding.atomic6
        atomic7 = binding.atomic7
        atomic8 = binding.atomic8
        atomic9 = binding.atomic9
        atomic10 = binding.atomic10

        atomic1.setOnClickListener { startActivity("atomic1") }
        atomic2.setOnClickListener { startActivity("atomic2") }
        atomic3.setOnClickListener { startActivity("atomic3") }
        atomic4.setOnClickListener { startActivity("atomic4") }
        atomic5.setOnClickListener { startActivity("atomic5") }
        atomic6.setOnClickListener { startActivity("atomic6") }
        atomic7.setOnClickListener { startActivity("atomic7") }
        atomic8.setOnClickListener { startActivity("atomic8") }
        atomic9.setOnClickListener { startActivity("atomic9") }
        atomic10.setOnClickListener { startActivity("atomic10") }

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
                        atomic1.setTime(h, m, s)
                        atomic2.setTime(h, m, s)
                        atomic3.setTime(h, m, s)
                        atomic4.setTime(h, m, s)
                        atomic5.setTime(h, m, s)
                        atomic6.setTime(h, m, s)
                        atomic7.setTime(h, m, s)
                        atomic8.setTime(h, m, s)
                        atomic9.setTime(h, m, s)
                        atomic10.setTime(h, m, s)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun startActivity(data: String) {
        Intent(this@AtomicClockActivity, AtomicClockDetailActivity::class.java).apply {
            putExtra("atomic", data)
            startActivity(this)
        }
    }
}