package com.alwaysondisplay.analogclock.digital.nightclock.atomic

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
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityAtomicClockDetailBinding
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences
import com.leondzn.simpleanalogclock.SimpleAnalogClock
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AtomicClockDetailActivity : AppCompatActivity() {

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
    private lateinit var binding: ActivityAtomicClockDetailBinding
    private var atomicNumber: String? = null
    private lateinit var batteryLevelTv: TextView
    private lateinit var applyBtn: TextView
    private lateinit var previewBtn: TextView
    private lateinit var timeLayout: LinearLayout
    private lateinit var batteryLayout: LinearLayout
    private lateinit var optionsLayout: LinearLayout
    private lateinit var abstractLayout: LinearLayout
    private var preferences: Preferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtomicClockDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        if (intent.extras != null) {
            atomicNumber = intent?.extras?.getString("atomic")
        }

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

        timeLayout = binding.timeLayout
        batteryLayout = binding.batteryLayout
        optionsLayout = binding.optionsLayout
        abstractLayout = binding.abstractLayout
        batteryLevelTv = binding.batteryLevelTv
        applyBtn = binding.applyBtn
        previewBtn = binding.previewBtn

        previewBtn.setOnClickListener {
            timeLayout.visibility = View.VISIBLE
            batteryLayout.visibility = View.VISIBLE
            optionsLayout.visibility = View.GONE
        }

        applyBtn.setOnClickListener {
            preferences = Preferences(this)
            preferences?.setAtomicApply(atomicNumber)
            preferences?.setCheckActivity("atomic")
            Toast.makeText(this, "Set As Always On Display", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@AtomicClockDetailActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        abstractLayout.setOnClickListener {
            timeLayout.visibility = View.GONE
            optionsLayout.visibility = View.VISIBLE
            batteryLayout.visibility = View.GONE
        }

        val iFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(mBroadcastReceiver, iFilter)

        when (atomicNumber) {
            "atomic1" -> setClocksVisibility(
                atomic1,
                atomic2,
                atomic3,
                atomic4,
                atomic5,
                atomic6,
                atomic7,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic2" -> setClocksVisibility(
                atomic2,
                atomic1,
                atomic3,
                atomic4,
                atomic5,
                atomic6,
                atomic7,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic3" -> setClocksVisibility(
                atomic3,
                atomic2,
                atomic1,
                atomic4,
                atomic5,
                atomic6,
                atomic7,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic4" -> setClocksVisibility(
                atomic4,
                atomic2,
                atomic3,
                atomic1,
                atomic5,
                atomic6,
                atomic7,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic5" -> setClocksVisibility(
                atomic5,
                atomic2,
                atomic3,
                atomic4,
                atomic1,
                atomic6,
                atomic7,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic6" -> setClocksVisibility(
                atomic6,
                atomic2,
                atomic3,
                atomic4,
                atomic5,
                atomic1,
                atomic7,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic7" -> setClocksVisibility(
                atomic7,
                atomic2,
                atomic3,
                atomic4,
                atomic5,
                atomic6,
                atomic1,
                atomic8,
                atomic9,
                atomic10
            )

            "atomic8" -> setClocksVisibility(
                atomic8,
                atomic2,
                atomic3,
                atomic4,
                atomic5,
                atomic6,
                atomic7,
                atomic1,
                atomic9,
                atomic10
            )

            "atomic9" -> setClocksVisibility(
                atomic9,
                atomic2,
                atomic3,
                atomic4,
                atomic5,
                atomic6,
                atomic7,
                atomic8,
                atomic1,
                atomic10
            )

            "atomic10" -> setClocksVisibility(
                atomic10,
                atomic2,
                atomic3,
                atomic4,
                atomic5,
                atomic6,
                atomic7,
                atomic8,
                atomic9,
                atomic1
            )
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

    private fun setClocksVisibility(
        mainClock: SimpleAnalogClock, vararg otherClocks: SimpleAnalogClock
    ) {
        mainClock.visibility = View.VISIBLE
        for (clock in otherClocks) {
            clock.visibility = View.GONE
        }
    }
}