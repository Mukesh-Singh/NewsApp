package com.app.news.ui.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Singleton
class ViewModelFactory
@Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    private val TAG = "ViewModelFactory"

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator =
            creators[modelClass] ?: creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }!!.value
            ?: throw IllegalArgumentException("Unknown model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            Log.e(TAG, "Error is here : " + e.message, e)
            throw RuntimeException(e)
        }
    }
}