package com.alwaysondisplay.analogclock.digital.nightclock.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.CompoundButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.alwaysondisplay.analogclock.digital.nightclock.abstract.SelectAbstractClockActivity
import com.alwaysondisplay.analogclock.digital.nightclock.atomic.AtomicClockActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityMainBinding
import com.alwaysondisplay.analogclock.digital.nightclock.digital.DigitalClockActivity
import com.alwaysondisplay.analogclock.digital.nightclock.neon.NeonClocksActivity
import com.alwaysondisplay.analogclock.digital.nightclock.service.OverlayService
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var serviceSwitch: SwitchCompat? = null
    private var preferences: Preferences? = null
    private var onOff: String? = null
    private var intentService: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        preferences = Preferences(this)

        serviceSwitch = binding.serviceSwitch
        intentService = Intent(this@MainActivity, OverlayService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                getResult.launch(
                    Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(
                            "package:$packageName"
                        )
                    )
                )
            }
        }

        onOff = preferences!!.getOnOff()
        if (onOff == "no") {
            serviceSwitch!!.isChecked = true
            startService(intentService)
        } else {
            serviceSwitch!!.isChecked = false
            stopService(intentService)
        }
        serviceSwitch!!.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                startService(intentService)
                preferences!!.setOnOff("no")
            } else {
                stopService(intentService)
                preferences!!.setOnOff("'yes'")
            }
        }

        binding.linearDigital.setOnClickListener {
            startActivity(Intent(this@MainActivity, DigitalClockActivity::class.java))
        }
        binding.linearAbstract.setOnClickListener {
            startActivity(Intent(this@MainActivity, SelectAbstractClockActivity::class.java))
        }
        binding.linearNeon.setOnClickListener {
            startActivity(Intent(this@MainActivity, NeonClocksActivity::class.java))
        }
        binding.linearAtomic.setOnClickListener {
            startActivity(Intent(this@MainActivity, AtomicClockActivity::class.java))
        }
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode != Activity.RESULT_OK) {
            serviceSwitch?.isChecked = true
            startService(intentService)
        }
    }
}