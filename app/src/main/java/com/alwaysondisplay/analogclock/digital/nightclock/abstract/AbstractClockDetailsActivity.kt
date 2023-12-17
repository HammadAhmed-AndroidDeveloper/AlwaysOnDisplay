package com.alwaysondisplay.analogclock.digital.nightclock.abstract

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.activities.MainActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityAbstractClockDetailsBinding
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences
import com.leondzn.simpleanalogclock.SimpleAnalogClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AbstractClockDetailsActivity : AppCompatActivity() {

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
    private lateinit var binding: ActivityAbstractClockDetailsBinding
    private var abstractNumber: String? = null

    private lateinit var batteryLevelTv: TextView
    private lateinit var applyBtn: TextView
    private lateinit var previewBtn: TextView
    private lateinit var linear_time: LinearLayout
    private lateinit var batteryLayout: LinearLayout
    private lateinit var optionsLayout: LinearLayout
    private lateinit var abstractLayout: LinearLayout
    private lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAbstractClockDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        if (intent.extras != null) {
            abstractNumber = intent?.extras?.getString("abstract")
        }

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
        linear_time = binding.timeLayout

        batteryLayout = binding.batteryLayout
        optionsLayout = binding.optionsLayout
        abstractLayout = binding.abstractLayout
        batteryLevelTv = binding.batteryLevelTv
        applyBtn = binding.applyBtn
        previewBtn = binding.previewBtn

        previewBtn.setOnClickListener {
            linear_time.visibility = View.VISIBLE
            batteryLayout.visibility = View.VISIBLE
            optionsLayout.visibility = View.GONE
        }

        applyBtn.setOnClickListener {
            preferences = Preferences(this@AbstractClockDetailsActivity)
            preferences.setAbstractApply(abstractNumber)
            preferences.setCheckActivity("abstract")
            Toast.makeText(
                this@AbstractClockDetailsActivity, "Set As Always On Display", Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(this@AbstractClockDetailsActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        abstractLayout.setOnClickListener {
            linear_time.visibility = View.GONE
            optionsLayout.visibility = View.VISIBLE
            batteryLayout.visibility = View.GONE
        }

        val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(mBroadcastReceiver, iFilter)

        when (abstractNumber) {
            "abstract1" -> setClocksVisibility(abstract1, abstract2, abstract3, abstract4, abstract5, abstract6, abstract7, abstract8, abstract9, abstract10)
            "abstract2" -> setClocksVisibility(abstract2, abstract1, abstract3, abstract4, abstract5, abstract6, abstract7, abstract8, abstract9, abstract10)
            "abstract3" -> setClocksVisibility(abstract3, abstract2, abstract1, abstract4, abstract5, abstract6, abstract7, abstract8, abstract9, abstract10)
            "abstract4" -> setClocksVisibility(abstract4, abstract2, abstract3, abstract1, abstract5, abstract6, abstract7, abstract8, abstract9, abstract10)
            "abstract5" -> setClocksVisibility(abstract5, abstract2, abstract3, abstract4, abstract1, abstract6, abstract7, abstract8, abstract9, abstract10)
            "abstract6" -> setClocksVisibility(abstract6, abstract2, abstract3, abstract4, abstract5, abstract1, abstract7, abstract8, abstract9, abstract10)
            "abstract7" -> setClocksVisibility(abstract7, abstract2, abstract3, abstract4, abstract5, abstract6, abstract1, abstract8, abstract9, abstract10)
            "abstract8" -> setClocksVisibility(abstract8, abstract2, abstract3, abstract4, abstract5, abstract6, abstract7, abstract1, abstract9, abstract10)
            "abstract9" -> setClocksVisibility(abstract9, abstract2, abstract3, abstract4, abstract5, abstract6, abstract7, abstract8, abstract1, abstract10)
            "abstract10" -> setClocksVisibility(abstract10, abstract2, abstract3, abstract4, abstract5, abstract6, abstract7, abstract8, abstract9, abstract1)
        }

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

    private val mBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context, intent: Intent) {
            var chargingStatus = ""

            when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                BatteryManager.BATTERY_STATUS_CHARGING -> chargingStatus = "Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> chargingStatus = "Not Charging"
                BatteryManager.BATTERY_STATUS_FULL -> chargingStatus = "Battery Full"
                BatteryManager.BATTERY_STATUS_UNKNOWN -> chargingStatus = "Unknown"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> chargingStatus = "Not Charging"
            }

            batteryLevelTv.text =
                "$chargingStatus ${intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)} %"
        }
    }

    private fun setClocksVisibility(mainClock: SimpleAnalogClock, vararg otherClocks: SimpleAnalogClock) {
        mainClock.visibility = View.VISIBLE
        for (clock in otherClocks) {
            clock.visibility = View.GONE
        }
    }
}