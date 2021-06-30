package com.app.news.data.remote

import com.app.news.data.model.NewsResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Mukesh on 30-Jun-21.
 */
interface WebService {
    @GET("top-headlines?country=in")
    fun getNews(): Observable<Response<NewsResponse>>
}