package com.app.news.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by Mukesh on 30-Jun-21.
 */
object ActivityUtils {
    @JvmStatic
    fun replaceFragmentInActivity(
        fragmentManager: FragmentManager?,
        fragment: Fragment,
        frameId: Int,
        addToBackStack: Boolean,
        tag: String?
    ) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(frameId, fragment, tag)
        if (addToBackStack) transaction.addToBackStack(tag)
        transaction.commit()
    }
}