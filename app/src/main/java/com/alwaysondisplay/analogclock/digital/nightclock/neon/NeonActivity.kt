package com.alwaysondisplay.analogclock.digital.nightclock.neon

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
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityNeonBinding
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences
import com.leondzn.simpleanalogclock.SimpleAnalogClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NeonActivity : AppCompatActivity() {

    private lateinit var neon1: SimpleAnalogClock
    private lateinit var neon2: SimpleAnalogClock
    private lateinit var neon3: SimpleAnalogClock
    private lateinit var neon4: SimpleAnalogClock
    private lateinit var neon5: SimpleAnalogClock
    private lateinit var neon6: SimpleAnalogClock
    private lateinit var neon7: SimpleAnalogClock
    private lateinit var neon8: SimpleAnalogClock
    private lateinit var neon9: SimpleAnalogClock
    private lateinit var neon10: SimpleAnalogClock
    private var neon: String? = null
    private lateinit var batteryLevelTv: TextView
    private lateinit var timeLayout: LinearLayout
    private lateinit var batteryLayout: LinearLayout
    private lateinit var abstractLayout: LinearLayout
    private var preferences: Preferences? = null
    private lateinit var binding: ActivityNeonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNeonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)

        preferences = Preferences(this)

        neon1 = binding.neon1
        neon2 = binding.neon2
        neon3 = binding.neon3
        neon4 = binding.neon4
        neon5 = binding.neon5
        neon6 = binding.neon6
        neon7 = binding.neon7
        neon8 = binding.neon8
        neon9 = binding.neon9
        neon10 = binding.neon10

        timeLayout = binding.timeLayout
        batteryLayout = binding.batteryLayout
        abstractLayout = binding.abstractLayout
        batteryLevelTv = binding.batteryLevelTv

        val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(mBroadcastReceiver, iFilter)
        neon = preferences?.getNeonApply()

        when (neon) {
            "neon1" -> setClocksVisibility(neon1, neon2, neon3, neon4, neon5, neon6, neon7, neon8, neon9, neon10)
            "neon2" -> setClocksVisibility(neon2, neon1, neon3, neon4, neon5, neon6, neon7, neon8, neon9, neon10)
            "neon3" -> setClocksVisibility(neon3, neon2, neon1, neon4, neon5, neon6, neon7, neon8, neon9, neon10)
            "neon4" -> setClocksVisibility(neon4, neon2, neon3, neon1, neon5, neon6, neon7, neon8, neon9, neon10)
            "neon5" -> setClocksVisibility(neon5, neon2, neon3, neon4, neon1, neon6, neon7, neon8, neon9, neon10)
            "neon6" -> setClocksVisibility(neon6, neon2, neon3, neon4, neon5, neon1, neon7, neon8, neon9, neon10)
            "neon7" -> setClocksVisibility(neon7, neon2, neon3, neon4, neon5, neon6, neon1, neon8, neon9, neon10)
            "neon8" -> setClocksVisibility(neon8, neon2, neon3, neon4, neon5, neon6, neon7, neon1, neon9, neon10)
            "neon9" -> setClocksVisibility(neon9, neon2, neon3, neon4, neon5, neon6, neon7, neon8, neon1, neon10)
            "neon10" -> setClocksVisibility(neon10, neon2, neon3, neon4, neon5, neon6, neon7, neon8, neon9, neon1)
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
                        neon1.setTime(h, m, s)
                        neon2.setTime(h, m, s)
                        neon3.setTime(h, m, s)
                        neon4.setTime(h, m, s)
                        neon5.setTime(h, m, s)
                        neon6.setTime(h, m, s)
                        neon7.setTime(h, m, s)
                        neon8.setTime(h, m, s)
                        neon9.setTime(h, m, s)
                        neon10.setTime(h, m, s)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun setClocksVisibility(
        mainClock: SimpleAnalogClock, vararg otherClocks: SimpleAnalogClock
    ) {
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