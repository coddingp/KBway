package com.example.kbway.root


import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import com.example.kbway.BackToSettingsScreenMail
import com.example.kbway.FirstScreenMail
import com.example.kbway.NightModeMail
import com.example.kbway.common.mvp.BaseActivity
import com.example.kbway.userFirstScreen.UserFirstFragment
import com.example.kbway.userRoute.ui.UserRouteFragment
import com.example.kbway.userSettings.SettingsFragment

class RootActivity : BaseActivity(), NightModeMail, FirstScreenMail, BackToSettingsScreenMail {

    private val sharedActivityPreference = "Activity Preference Data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.kbway.R.layout.root_activity)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    }

    override fun onStart() {
        super.onStart()
        val activitySharedPreferences: SharedPreferences =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)

        this.delegate.localNightMode =
            activitySharedPreferences.getInt("mode", 1)

        if (activitySharedPreferences.getString(
                "BackToSettings?",
                "Go to routes"
            ) == "Go back to settings"
        ) {
            sharedPreferencesEditor("BackToSettings?", "Go to routes")
            changeFragment(SettingsFragment(), com.example.kbway.R.id.contentContainer)
        } else {
            if (activitySharedPreferences.getString("firstScreen", "0")!!.toInt() > 0) {
                changeFragment(UserRouteFragment(), com.example.kbway.R.id.contentContainer)
            } else {
                changeFragment(UserFirstFragment(), com.example.kbway.R.id.contentContainer)
            }
        }
    }

    override fun nightModeMail(mail: Int) {

        val editor3: SharedPreferences.Editor =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .edit()
        editor3.putInt("mode", mail)
        editor3.apply()
    }

    override fun firstScreenMail(mail: Int) {
        sharedPreferencesEditor("firstScreen", mail.toString())
    }

    override fun backToSettingsScreenMail(mail: String) {
        sharedPreferencesEditor("BackToSettings?", mail)
    }

    private fun sharedPreferencesEditor(mapName: String, value: String) {
        val editor: SharedPreferences.Editor =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .edit()
        editor.putString(mapName, value)
        editor.apply()
    }
}