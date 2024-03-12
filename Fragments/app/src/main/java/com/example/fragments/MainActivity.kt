package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomnav: BottomNavigationView = findViewById(R.id.bottomNav)

        handleFragment()
        bottomnav.setOnItemSelectedListener {
            val id = it.itemId
            when (id) {

                R.id.bottomHome -> {
                    handleFragment()
                }

                R.id.bottomUser -> {
                    handleFragment(UserFragment())
                }

                R.id.bottomCart -> {
                    handleFragment(CartFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun handleFragment(fragment: Fragment = HomeFragment()) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutFragmContainerView, fragment)
            .commit()
    }
}