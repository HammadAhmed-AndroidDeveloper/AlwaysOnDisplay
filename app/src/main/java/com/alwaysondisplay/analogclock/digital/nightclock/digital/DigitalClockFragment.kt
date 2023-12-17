package com.alwaysondisplay.analogclock.digital.nightclock.digital

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.grabner.circleprogress.CircleProgressView
import com.alwaysondisplay.analogclock.digital.nightclock.R
import com.alwaysondisplay.analogclock.digital.nightclock.service.Preferences
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DigitalClockFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = Preferences(requireActivity())

        when (preferences.getDigitalFragment()) {
            "digital3" -> {
                val second3 = view.findViewById<CircularProgressBar>(R.id.sec)
                val minute3 = view.findViewById<CircleProgressView>(R.id.cpv_second3)

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

            "digital5" -> {
                val second5 = view.findViewById<CircularProgressBar>(R.id.SecProgressBar)
                val minute5 = view.findViewById<CircularProgressBar>(R.id.MinProgressBar)

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
                val second6 = view.findViewById<CircleProgressView>(R.id.cpv_second6)
                val minute6 = view.findViewById<CircleProgressView>(R.id.cpv_minute6)

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
                val second7 = view.findViewById<CircleProgressView>(R.id.cpv_second7)
                val run = true
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
                                second7.setValue(currentTime.toFloat())
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }.start()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val preferences = Preferences(requireActivity())
        return when (preferences.getDigitalFragment()) {
            "digital1" -> inflater.inflate(R.layout.digital_clock_layout_1, container, false)
            "digital2" -> inflater.inflate(R.layout.digital_clock_layout_2, container, false)
            "digital3" -> inflater.inflate(R.layout.digital_clock_layout_3, container, false)
            "digital4" -> inflater.inflate(R.layout.digital_clock_layout_4, container, false)
            "digital5" -> inflater.inflate(R.layout.digital_clock_layout_5, container, false)
            "digital6" -> inflater.inflate(R.layout.digital_clock_layout_6, container, false)
            "digital7" -> inflater.inflate(R.layout.digital_clock_layout_7, container, false)
            "digital8" -> inflater.inflate(R.layout.digital_clock_layout_8, container, false)
            "digital9" -> inflater.inflate(R.layout.digital_clock_layout_9, container, false)
            "digital10" -> inflater.inflate(R.layout.digital_clock_layout_10, container, false)
            else -> inflater.inflate(R.layout.fragment_digital_clock, container, false)
        }
    }
}