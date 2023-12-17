package com.alwaysondisplay.analogclock.digital.nightclock.digital

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import at.grabner.circleprogress.CircleProgressView
import com.alwaysondisplay.analogclock.digital.nightclock.R
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SelectDigitalClocksActivity : AppCompatActivity() {

    var preferences: Preferences? = null
    var digital: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)

        preferences = Preferences(this)

        digital = preferences!!.getDigitalNumber()
        when (digital) {
            "digital1" -> setContentView(R.layout.digital_clock_layout_1)
            "digital2" -> setContentView(R.layout.digital_clock_layout_2)
            "digital3" -> {
                setContentView(R.layout.digital_clock_layout_3)
                val second3 = findViewById<CircularProgressBar>(R.id.sec)
                val minute3 = findViewById<CircleProgressView>(R.id.cpv_second3)
                val mHandler = Handler(Looper.getMainLooper())
                Thread {
                    while (true) {
                        try {
                            Thread.sleep(1000)
                            mHandler.post {
                                val seconds =
                                    SimpleDateFormat("ss", Locale.getDefault()).format(Date())
                                val minute =
                                    SimpleDateFormat("mm", Locale.getDefault()).format(Date())
                                second3.progress = seconds.toFloat()
                                minute3.setValue(minute.toFloat())
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }

            "digital4" -> setContentView(R.layout.digital_clock_layout_4)
            "digital5" -> {
                setContentView(R.layout.digital_clock_layout_5)
                val second5 = findViewById<CircularProgressBar>(R.id.SecProgressBar)
                val minute5 = findViewById<CircularProgressBar>(R.id.MinProgressBar)
                val mHandler = Handler(Looper.getMainLooper())
                Thread {
                    while (true) {
                        try {
                            Thread.sleep(1000)
                            mHandler.post {
                                val currentTime =
                                    SimpleDateFormat("ss", Locale.getDefault()).format(
                                        Date()
                                    )
                                val minute =
                                    SimpleDateFormat("mm", Locale.getDefault()).format(Date())
                                second5.progress = currentTime.toFloat()
                                minute5.progress = minute.toFloat()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }

            "digital6" -> {
                setContentView(R.layout.digital_clock_layout_6)
                val second6 = findViewById<CircleProgressView>(R.id.cpv_second6)
                val minute6 = findViewById<CircleProgressView>(R.id.cpv_minute6)
                val run = true
                val mHandler = Handler(Looper.getMainLooper())
                Thread {
                    while (run) {
                        try {
                            Thread.sleep(1000)
                            mHandler.post {
                                val currentTime =
                                    SimpleDateFormat("ss", Locale.getDefault()).format(
                                        Date()
                                    )
                                val minute =
                                    SimpleDateFormat("mm", Locale.getDefault()).format(Date())
                                second6.setValue(currentTime.toFloat())
                                minute6.setValue(minute.toFloat())
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }

            "digital7" -> {
                setContentView(R.layout.digital_clock_layout_7)
                val second7 = findViewById<CircleProgressView>(R.id.cpv_second7)
                val mHandler = Handler(Looper.getMainLooper())
                Thread {
                    while (true) {
                        try {
                            Thread.sleep(1000)
                            mHandler.post {
                                val currentTime =
                                    SimpleDateFormat("ss", Locale.getDefault()).format(
                                        Date()
                                    )
                                second7.setValue(currentTime.toFloat())
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }

            "digital8" -> setContentView(R.layout.digital_clock_layout_8)
            "digital9" -> setContentView(R.layout.digital_clock_layout_9)
            "digital10" -> setContentView(R.layout.digital_clock_layout_10)
        }
    }
}