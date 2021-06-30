package com.app.news.di.builder

import com.app.news.ui.news.MainActivity
import com.app.news.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector()
    abstract fun bindSplashActivity(): SplashActivity
}