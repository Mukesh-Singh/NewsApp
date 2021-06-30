package com.app.news.di.component

import android.app.Application
import com.app.news.NewsApplication
import com.app.news.di.builder.ActivityBuilder
import com.app.news.di.module.ContextModule
import com.app.news.di.module.DatabaseModule
import com.app.news.di.module.NetworkModule
import com.app.news.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ActivityBuilder::class,
        ContextModule::class,
        NetworkModule::class
        ]
)
interface ApplicationComponent : AndroidInjector<NewsApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}