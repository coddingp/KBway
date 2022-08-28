package com.example.kbway.userFirstScreen

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Toast.makeText(context, "onViewCreated", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
//        Toast.makeText(context, "onViewCreated", Toast.LENGTH_SHORT).show()
        binding.startUserTextView.setOnClickListener {
            changeFragment(UserRouteFragment(), R.id.contentContainer)
        }
    }



}

