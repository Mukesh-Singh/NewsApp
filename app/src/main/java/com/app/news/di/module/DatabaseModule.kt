package com.app.news.di.module

import android.app.Application
import androidx.room.Room
import com.app.news.data.local.AppDatabase
import com.app.news.data.local.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDatabase): NewsDao {
        return appDataBase.newsDao()
    }
}