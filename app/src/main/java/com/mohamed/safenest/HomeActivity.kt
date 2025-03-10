package com.mohamed.safenest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.messaging.FirebaseMessaging
import com.mohamed.safenest.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityHomeBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTabs()
        alerts()
    }

    private fun alerts()
    {
        FirebaseMessaging.getInstance().subscribeToTopic("water_alerts")
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                {
                    Log.d("FCM", "Water leak alerts are active.")
                } else
                {
                    Log.e("FCM", "Failed to access notifications", task.exception)
                }
            }


    }

    private fun setupTabs()
    {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId)
            {
                R.id.Help ->
                {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HelpFragment())
                        .commit()
                }

                R.id.Home ->
                {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                }

                R.id.Profile ->
                {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                }

                R.id.Alerts ->
                {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AlertFragment())
                        .commit()
                }

                R.id.Contacts ->
                {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, ContactsFragment())
                        .commit()
                }
            }
            return@setOnItemSelectedListener true
        }

        binding.bottomNav.selectedItemId = R.id.Home

    }
}
