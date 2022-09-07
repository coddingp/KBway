package com.example.kbway.root


import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.kbway.PostMan
import com.example.kbway.common.mvp.BaseActivity
import com.example.kbway.userFirstScreen.UserFirstFragment
import com.example.kbway.userRoute.ui.UserRouteFragment

class RootActivity : BaseActivity(), PostMan {

    private val sharedActivityPreference = "count"

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
        this.delegate.localNightMode = activitySharedPreferences.getInt("mode", 2)
        if (activitySharedPreferences.getInt("value", 0) > 0) {
            changeFragment(UserRouteFragment(), com.example.kbway.R.id.contentContainer)
        } else {
            changeFragment(UserFirstFragment(), com.example.kbway.R.id.contentContainer)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val editor: SharedPreferences.Editor =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .edit()
        editor.putInt("value", 1)
        editor.apply()
    }

    override fun fragmentMail(mail: Int) {
        this.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES/*mail*/

        val editor3: SharedPreferences.Editor =
            this@RootActivity.getSharedPreferences(sharedActivityPreference, MODE_PRIVATE)
                .edit()
        editor3.putInt("mode", mail)
        editor3.apply()
    }
}