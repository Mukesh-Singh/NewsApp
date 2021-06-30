package com.app.news.di.builder

import androidx.lifecycle.ViewModel
import com.app.news.di.qualifier.ViewModelKey
import com.app.news.ui.news.list.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel

}