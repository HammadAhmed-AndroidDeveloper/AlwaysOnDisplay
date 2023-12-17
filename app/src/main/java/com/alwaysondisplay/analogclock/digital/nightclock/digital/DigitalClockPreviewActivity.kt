package com.alwaysondisplay.analogclock.digital.nightclock.digital

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alwaysondisplay.analogclock.digital.nightclock.R
import com.alwaysondisplay.analogclock.digital.nightclock.activities.MainActivity
import com.alwaysondisplay.analogclock.digital.nightclock.databinding.ActivityDigitalClockPreviewBinding
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences

class DigitalClockPreviewActivity : AppCompatActivity() {

    private var clock: String? = null
    private var previewBtn: TextView? = null
    private var applyBtn: TextView? = null
    private lateinit var binding: ActivityDigitalClockPreviewBinding
    private var preferences: Preferences? = null
    private var digitalClockFragment: FrameLayout? = null
    private var optionsLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDigitalClockPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)

        if (intent.extras != null) {
            clock = intent.extras!!.getString("digital")
        }

        preferences = Preferences(this)

        previewBtn = binding.previewBtn
        applyBtn = binding.applyBtn
        digitalClockFragment = binding.digitalClockFragment
        optionsLayout = binding.optionsLayout

        preferences!!.setDigitalFragment(clock)

        supportFragmentManager.beginTransaction()
            .add(R.id.digitalClockFragment, DigitalClockFragment()).commit()

        previewBtn!!.setOnClickListener {
            optionsLayout!!.visibility = View.GONE
        }

        applyBtn!!.setOnClickListener {
            preferences?.setCheckActivity("digital")
            preferences?.setDigitalNumber(clock)
            Toast.makeText(this, "Set As Always On Display", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@DigitalClockPreviewActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        digitalClockFragment?.setOnClickListener {
            optionsLayout!!.visibility = View.VISIBLE
        }
    }
}