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
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityAbstractClockBinding
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences
import com.leondzn.simpleanalogclock.SimpleAnalogClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AbstractClockActivity : AppCompatActivity() {

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
    private var abstractNumber: String? = null
    private lateinit var batteryLevelTv: TextView
    private lateinit var timeLayout: LinearLayout
    private lateinit var batteryLayout: LinearLayout
    private lateinit var abstractLayout: LinearLayout
    private lateinit var binding: ActivityAbstractClockBinding
    var preferences: Preferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbstractClockBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        supportActionBar?.hide()

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        preferences = Preferences(this)

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

        timeLayout = binding.timeLayout
        batteryLayout = binding.batteryLayout
        abstractLayout = binding.abstractLayout
        batteryLevelTv = binding.batteryLevelTv

        val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(mBroadcastReceiver, iFilter)
        abstractNumber = preferences?.getAbstractApply()

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

    private fun setClocksVisibility(mainClock: SimpleAnalogClock, vararg otherClocks: SimpleAnalogClock) {
        mainClock.visibility = View.VISIBLE
        for (clock in otherClocks) {
            clock.visibility = View.GONE
        }
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

            batteryLevelTv.text = "$chargingStatus ${intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)} %"
        }
    }
}