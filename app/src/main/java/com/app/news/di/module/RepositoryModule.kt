package com.app.news.di.module

import com.app.news.data.local.AppDatabase
import com.app.news.data.remote.WebService
import com.app.news.data.repository.NewsRepository
import com.app.news.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {


    @Provides
    @Singleton
    fun provideNewsRepository(webService: WebService, database: AppDatabase): NewsRepository {
        return NewsRepositoryImpl(webService, database)
    }
}