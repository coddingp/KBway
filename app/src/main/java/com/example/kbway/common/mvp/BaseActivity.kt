package com.example.kbway.common.mvp

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

abstract class BaseActivity : AppCompatActivity() {

    protected fun changeFragment(fragment1: Fragment, id1: Int) {
        val fragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(id1, fragment1)
            .commit()
    }


    protected fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            val firstFragment = manager.getBackStackEntryAt(0)
            manager.popBackStack(firstFragment.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }


}