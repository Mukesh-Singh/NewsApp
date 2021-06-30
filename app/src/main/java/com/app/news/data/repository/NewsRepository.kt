package com.app.news.data.repository

import com.app.news.data.Resource
import com.app.news.data.model.Article
import io.reactivex.disposables.Disposable

/**
 * Created by Mukesh on 30-Jun-21.
 */
interface NewsRepository {
    fun fetchArticles(
        success: (Resource<List<Article>>?) -> Unit
    ): Disposable
}