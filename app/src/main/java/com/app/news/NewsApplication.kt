package com.app.news

import android.content.Context
import com.app.news.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Mukesh on 30-Jun-21.
 */
class NewsApplication: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}