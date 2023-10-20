package com.gutib.activity4gutib

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gutib.activity4gutib.databinding.Activity4Binding

class Activity4 : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: Activity4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)
        setContentView(binding.root) // Fix the layout inflation

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        load()

        binding.save.setOnClickListener {
            save()
            Toast.makeText(this, "Choices Saved", Toast.LENGTH_LONG).show()
        }
    }

    private fun save() {
        // Store UI element states in SharedPreferences
        val preferencesEditor = sharedPreferences.edit()
        preferencesEditor.putString("email_address", binding.emailAddress.text.toString())
        preferencesEditor.putString("nickname", binding.nickname.text.toString())
        preferencesEditor.putBoolean("allow_push_notification", binding.checkBox.isChecked)
        preferencesEditor.putInt("selectedTheme", binding.themes.checkedRadioButtonId)
        preferencesEditor.apply()
    }

    private fun load() {
        // Load UI element states from SharedPreferences
        binding.emailAddress.setText(sharedPreferences.getString("email_address", ""))
        binding.nickname.setText(sharedPreferences.getString("nickname", ""))
        binding.checkBox.isChecked = sharedPreferences.getBoolean("allow_push_notification", false)

        val selectedThemeId = sharedPreferences.getInt("selectedTheme", -1)
        if (selectedThemeId != -1) {
            binding.themes.check(selectedThemeId)
        }
    }

}
