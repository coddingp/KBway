package com.example.kbway.userSettings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.kbway.NightModeMail
import com.example.kbway.R
import com.example.kbway.common.mvp.BaseFragment
import com.example.kbway.databinding.SettingsBinding


class SettingsFragment : BaseFragment(R.layout.user_route) {

    private lateinit var binding: SettingsBinding
    private val sharedPreferencesFile = "savedData"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val modeSharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE)
        (activity as AppCompatActivity).delegate.localNightMode =
            modeSharedPreferences.getInt("nightMode", AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE)
        binding.themeSwitch.isChecked = sharedPreferences.getBoolean("value", false)
    }

    override fun onResume() {
        super.onResume()
        binding.themeSwitch.setOnClickListener {
            if (binding.themeSwitch.isChecked) {
                (activity as AppCompatActivity).delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_YES

                val editor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE)
                        .edit()
                editor.putBoolean("value", true)
                editor.apply()

                val modeEditor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE)
                        .edit()
                modeEditor.putInt("nightMode", AppCompatDelegate.MODE_NIGHT_YES)
                modeEditor.apply()

                try {
                    (activity as NightModeMail).nightModeMail(AppCompatDelegate.MODE_NIGHT_YES)
                } catch (ignored: ClassCastException) {
                }

            } else {

                (activity as AppCompatActivity).delegate.localNightMode =
                    AppCompatDelegate.MODE_NIGHT_NO

                val editor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE)
                        .edit()
                editor.putBoolean("value", false)
                editor.apply()

                val modeEditor: SharedPreferences.Editor =
                    requireActivity().getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE)
                        .edit()
                modeEditor.putInt("nightMode", AppCompatDelegate.MODE_NIGHT_NO)
                modeEditor.apply()

                try {
                    (activity as NightModeMail).nightModeMail(AppCompatDelegate.MODE_NIGHT_NO)
                } catch (ignored: ClassCastException) {
                }
            }
        }

        binding.supportTextView.setOnClickListener {
            Toast.makeText(
                context,
                "Приносим свои извинения, на данный момент техническая поддержка не доступна!",
                Toast.LENGTH_LONG
            ).show()
        }
        binding.playStoreTextView.setOnClickListener {
            Toast.makeText(
                context,
                "Приносим свои извинения, оставть отзывы возможно только через 2 недели после начала использования приложения!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}