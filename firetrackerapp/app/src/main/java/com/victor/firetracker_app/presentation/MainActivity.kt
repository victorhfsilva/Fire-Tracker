package com.victor.firetracker_app.presentation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.victor.firetracker_app.presentation.adapters.PagerAdapter
import com.victor.firetracker_app.R
import com.victor.firetracker_app.databinding.ActivityMainBinding
import com.victor.firetracker_app.events.foreground_services.DataRequestForegroundService
import com.victor.firetracker_app.events.workers.RequestDataWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        binding.viewPager.adapter = PagerAdapter(this)

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        setContentView(view)
        setupListeners()
        setupPagerCallback()
        checkSMSPermission()
        setupEvents()
    }

    private fun setupEvents() {
        val dataRequestWorkRequest =
            PeriodicWorkRequestBuilder<RequestDataWorker>(5, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(dataRequestWorkRequest)

        val foregroundServiceIntent = Intent(this, DataRequestForegroundService::class.java)
        ContextCompat.startForegroundService(this, foregroundServiceIntent)
    }

    fun setupListeners() {

        binding.bottomNavBar.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_bar_firetracker_menu -> binding.viewPager.setCurrentItem(0, true)
                R.id.bottom_bar_config_menu -> binding.viewPager.setCurrentItem(1, true)
                R.id.bottom_bar_about_menu -> binding.viewPager.setCurrentItem(2, true)
            }
            true
        }

        binding.topAppBar.setNavigationOnClickListener {
            binding.viewPager.setCurrentItem(0, true)
        }

        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.top_bar_firetracker_menu-> binding.viewPager.setCurrentItem(0, true)
                R.id.top_bar_config_menu -> binding.viewPager.setCurrentItem(1, true)
                R.id.top_bar_about_menu -> binding.viewPager.setCurrentItem(2, true)
            }
            true
        }

    }

    fun setupPagerCallback() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavBar.selectedItemId = R.id.bottom_bar_firetracker_menu
                    1 -> binding.bottomNavBar.selectedItemId = R.id.bottom_bar_config_menu
                    2 -> binding.bottomNavBar.selectedItemId = R.id.bottom_bar_about_menu
                }
            }
        })
    }

    fun checkSMSPermission() {
        val SEND_SMS_PERMISSION_REQUEST_CODE = 1

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                SEND_SMS_PERMISSION_REQUEST_CODE
            )
        }
    }
}