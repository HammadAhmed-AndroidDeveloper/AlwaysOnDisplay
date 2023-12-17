package com.alwaysondisplay.analogclock.digital.nightclock.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.alwaysondisplay.analogclock.digital.nightclock.R
import com.alwaysondisplay.analogclock.digital.nightclock.abstract.AbstractClockActivity
import com.alwaysondisplay.analogclock.digital.nightclock.atomic.AtomicClocksActivity
import com.alwaysondisplay.analogclock.digital.nightclock.digital.SelectDigitalClocksActivity
import com.alwaysondisplay.analogclock.digital.nightclock.neon.NeonActivity

class OverlayService : Service() {
    var preferences: Preferences? = null
    val CHANNEL_ID = "alwaysOnDisplay"

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        registerOverlayReceiver()
        return super.onStartCommand(intent, flags, startId)
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel() {

        val channel = NotificationChannel(
            CHANNEL_ID, "Always On Display", NotificationManager.IMPORTANCE_DEFAULT
        )
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel()
            val notification =
                NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("" + R.string.app_name)
                    .setContentText("").build()
            startForeground(1, notification)
        }
    }

    override fun onDestroy() {
        unregisterOverlayReceiver()
        super.onDestroy()
    }

    private fun registerOverlayReceiver() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_OFF)
//        filter.addAction(ACTION_DEBUG)
        registerReceiver(overlayReceiver, filter)
    }

    private fun unregisterOverlayReceiver() {
        unregisterReceiver(overlayReceiver)
    }

    private val overlayReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_SCREEN_OFF) {
                showOverlayActivity(context)
            } else if (action == Intent.ACTION_USER_PRESENT) {
                showOverlayActivity(context)
            }
        }
    }

    private fun showOverlayActivity(context: Context) {
        preferences = Preferences(context)
        val check_activity = preferences!!.getCheckActivity()
        Toast.makeText(context, "" + check_activity, Toast.LENGTH_SHORT).show()
        when (check_activity) {
            "digital" -> {
                Intent(context, SelectDigitalClocksActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(this)
                }

            }

            "abstract" -> {
                Intent(context, AbstractClockActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(this)
                }
            }

            "neon" -> {
                Intent(context, NeonActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(this)
                }

            }

            "atomic" -> {
                Intent(context, AtomicClocksActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(this)
                }
            }
        }
    }

    companion object {
        private const val ACTION_DEBUG = "daichan4649.lockoverlay.action.DEBUG"
    }
}