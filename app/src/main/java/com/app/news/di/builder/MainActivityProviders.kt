package com.app.news.di.builder

import com.app.news.ui.news.details.NewsDetailsFragment
import com.app.news.ui.news.list.NewsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideNewsFragment(): NewsListFragment

    @ContributesAndroidInjector
    abstract fun provideNewsDetailsFragment(): NewsDetailsFragment

}