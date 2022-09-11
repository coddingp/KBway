package com.example.kbway.userFirstScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kbway.FirstScreenMail
import com.example.kbway.R
import com.example.kbway.common.mvp.BaseFragment
import com.example.kbway.databinding.UserFirstScreenBinding
import com.example.kbway.userRoute.ui.UserRouteFragment

class UserFirstFragment : BaseFragment(R.layout.user_first_screen) {

    lateinit var binding: UserFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserFirstScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.startUserTextView.setOnClickListener {
            changeFragment(UserRouteFragment(), R.id.contentContainer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        (activity as FirstScreenMail).firstScreenMail(1)
    }
}