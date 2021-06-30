package com.app.news.data.repository

import android.util.Log
import com.app.news.data.Resource
import com.app.news.data.local.AppDatabase
import com.app.news.data.model.Article
import com.app.news.data.remote.WebService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Mukesh on 30-Jun-21.
 */
class NewsRepositoryImpl @Inject constructor(
    private val webService: WebService,
    private val appDatabase: AppDatabase
) : NewsRepository {
    private val TAG: String = "NewsRepository";

    override fun fetchArticles(
        success: (Resource<List<Article>>?) -> Unit
    ): Disposable {
        return webService.getNews().concatMap { apiArticleResponse ->
            appDatabase.newsDao().insertArticles(apiArticleResponse.body()!!.articles)
            Observable.just(appDatabase.newsDao().all)
        }

            // }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                success(Resource.loading())
            }
            .subscribe(
                { result ->
                    success(Resource.success(result as List<Article>))
                },
                {
                    success(Resource.error(it.localizedMessage!!, appDatabase.newsDao().all))
                    Log.e(TAG, "Error- " + it.localizedMessage)
                }
            )

    }


}