package com.app.news.ui.news.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.news.data.Resource
import com.app.news.data.model.Article
import com.app.news.data.repository.NewsRepository
import com.app.news.ui.common.BaseViewModel
import javax.inject.Inject

/**
 * Created by Mukesh on 30-Jun-21.
 */
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : BaseViewModel() {
    private val TAG = "NewsViewModel"
    val newsData: MutableLiveData<List<Article>> by lazy { MutableLiveData<List<Article>>() }
    val error: MutableLiveData<String?> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val articleForDetails: MutableLiveData<Article> = MutableLiveData()

    init {
        fetchAllArticles()
    }



    private fun fetchAllArticles() {
        Log.d(TAG, "fetchAllArticles() called")
        newsRepository.fetchArticles {
            if (it != null) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.e(TAG, "Loading")
                        progress.value=true;
                    }
                    Resource.Status.ERROR -> {
                        Log.e(TAG, "Error" + it.data?.size)
                        progress.value=false;
                        newsData.value = it.data
                        if (it.data.isNullOrEmpty()){
                            error.value = it.message
                        }
                    }
                    Resource.Status.SUCCESS -> {
                        Log.e(TAG, "Success" + it.data?.size)
                        progress.value=false;
                        error.value = null
                        newsData.value = it.data
                    }
                }

            }
        }.also { compositeDisposable.add(it) }
    }

    fun refreshData() {
        fetchAllArticles()
    }


}