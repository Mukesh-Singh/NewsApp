package com.app.news.ui.splash

import android.content.Intent
import android.os.Bundle
import com.app.news.ui.news.MainActivity
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by Mukesh on 30-Jun-21.
 */
class SplashActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}