package com.app.news.di.qualifier

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@MustBeDocumented
@Target(
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR
)
annotation class ViewModelKey(val value: KClass<out ViewModel>)