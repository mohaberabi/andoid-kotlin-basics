package com.example.tablayoutviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabLayout)

        appBarLayout = findViewById(R.id.appBarLayout)
        viewPager = findViewById(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdpater(this)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, index: Int ->
            when (index) {
                0 -> tab.text = "First"
                1 -> tab.text = "Second"
                else -> tab.text = "Third"
            }
        }.attach()

    }
}