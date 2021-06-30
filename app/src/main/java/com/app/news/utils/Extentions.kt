package com.app.news.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Mukesh on 30-Jun-21.
 */

fun String.toDate(/*dateFormat: String = "EEE, dd MMM yyyy HH:mm:ss", */dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): Date? {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}