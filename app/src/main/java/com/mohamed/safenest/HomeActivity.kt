package com.mohamed.safenest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mohamed.safenest.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity()
{
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                        .replace(R.id.fragment_container, AlertsFragment())
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