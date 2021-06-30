package com.app.news.di.module

import android.content.Context
import com.app.news.BuildConfig
import com.app.news.data.remote.WebService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Mukesh on 30-Jun-21.
 */


const val BASE_URL = "https://newsapi.org/v2/"


@Module(includes = [ContextModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor, httpLoggingInterceptor: HttpLoggingInterceptor)
            : OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) builder.addInterceptor(httpLoggingInterceptor)
        builder

            .addInterceptor(interceptor)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor=HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor;
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Api-Key", BuildConfig.API_KEY)
                .build()
            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }
}