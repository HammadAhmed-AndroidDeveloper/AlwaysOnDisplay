package com.alwaysondisplay.analogclock.digital.nightclock.service

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val mySharedPreferences: SharedPreferences

    init {
        mySharedPreferences = context.getSharedPreferences("alwaysOnDisplayPrefs", Context.MODE_PRIVATE)
    }

    fun setDigitalFragment(value: String?) {
        mySharedPreferences.edit().putString("digital", value).apply()
    }

    fun getDigitalFragment(): String? {
        return mySharedPreferences.getString("digital", "digital1")
    }

    fun setDigitalNumber(value: String?) {
        mySharedPreferences.edit().putString("digital1", value).apply()
    }

    fun getDigitalNumber(): String? {
        return mySharedPreferences.getString("digital1", "digital1")
    }

    fun setCheckActivity(value: String?) {
        mySharedPreferences.edit().putString("activity", value).apply()
    }

    fun getCheckActivity(): String? {
        return mySharedPreferences.getString("activity", "digital")
    }

    fun setAbstractApply(value: String?) {
        mySharedPreferences.edit().putString("abstract", value).apply()
    }

    fun getAbstractApply(): String? {
        return mySharedPreferences.getString("abstract", "abstract1")
    }

    fun setNeonApply(value: String?) {
        mySharedPreferences.edit().putString("neon", value).apply()
    }

    fun getNeonApply(): String? {
        return mySharedPreferences.getString("neon", "neon1")
    }

    fun setAtomicApply(value: String?) {
        mySharedPreferences.edit().putString("atomic", value).apply()
    }

    fun getAtomicApply(): String? {
        return mySharedPreferences.getString("atomic", "atomic1")
    }

    fun setOnOff(value: String?) {
        mySharedPreferences.edit().putString("isActivated", value).apply()
    }

    fun getOnOff(): String? {
        return mySharedPreferences.getString("isActivated", "yes")
    }
}