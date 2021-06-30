package com.app.news.di.builder

import androidx.lifecycle.ViewModelProvider
import com.app.news.ui.common.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Module(includes = [ViewModelModule::class])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory)
            : ViewModelProvider.Factory
}