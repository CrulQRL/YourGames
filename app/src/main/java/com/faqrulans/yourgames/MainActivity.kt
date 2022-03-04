package com.faqrulans.yourgames

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q && isCurrentTaskRoot()) {
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }
    }

    private fun isCurrentTaskRoot() : Boolean {
        return isTaskRoot &&
                supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.backStackEntryCount ?: 0 == 0 &&
                supportFragmentManager.backStackEntryCount == 0
    }
}
