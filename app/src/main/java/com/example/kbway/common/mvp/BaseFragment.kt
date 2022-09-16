package com.example.kbway.common.mvp

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    fun changeFragment(fragment: Fragment, id: Int) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction1: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction1.addToBackStack(null)
            .replace(id, fragment)
            .commit()
    }

    fun clearBackStack() {
        val manager: FragmentManager = requireActivity().supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}