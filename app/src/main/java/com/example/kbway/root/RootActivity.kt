package com.example.kbway.root


import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.kbway.FirstScreenMail
import com.example.kbway.NightModeMail
import com.example.kbway.common.mvp.BaseActivity
import com.example.kbway.userFirstScreen.UserFirstFragment
import com.example.kbway.userRoute.ui.UserRouteFragment

class RootActivity : BaseActivity(), NightModeMail, FirstScreenMail {

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
            activitySharedPreferences.getInt("mode", AppCompatDelegate.MODE_NIGHT_NO)
        if (activitySharedPreferences.getInt("firstScreen", 0) > 0) {
            changeFragment(UserRouteFragment(), com.example.kbway.R.id.contentContainer)
        } else {
            changeFragment(UserFirstFragment(), com.example.kbway.R.id.contentContainer)
        }
    }

    override fun nightModeMail(mail: Int) {
        val editor3: SharedPreferences.Editor =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .edit()
        editor3.putInt("mode", mail)
        editor3.apply()
        this.delegate.localNightMode =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .getInt("mode", 1)
    }

    override fun firstScreenMail(mail: Int) {
        val editor4: SharedPreferences.Editor =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .edit()
        editor4.putInt("firstScreen", mail)
        editor4.apply()
    }
}