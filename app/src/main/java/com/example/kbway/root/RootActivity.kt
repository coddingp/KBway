package com.example.kbway.root


import android.os.Bundle
import android.view.WindowManager
import com.example.kbway.R
import com.example.kbway.common.mvp.BaseActivity
import com.example.kbway.userFirstScreen.UserFirstFragment
import com.example.kbway.userMap.UserMapFragment
import com.example.kbway.userRoute.ui.UserRouteFragment
import timber.log.Timber

class RootActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.root_activity)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val userFirstFragment = UserFirstFragment()
        try {
            changeFragment(userFirstFragment, R.id.contentContainer)
        } catch (t: Throwable) {
            Timber.i("$t")
        }
    }
}