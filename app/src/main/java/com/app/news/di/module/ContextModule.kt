package com.app.news.di.module

import android.app.Application
import android.content.Context
import com.app.news.di.builder.ViewModelBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module(includes = [ViewModelBuilder::class])
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}