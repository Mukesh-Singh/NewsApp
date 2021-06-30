package com.app.news.data.local

import androidx.room.TypeConverter
import com.app.news.data.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Mukesh on 30-Jun-21.
 */
class Converters {

    @TypeConverter
    fun fromSource(source: Source?): String? {
        return if (source == null) null else Gson().toJson(source)
    }

    @TypeConverter
    fun toSource(value: String?): Source? {
        return if (value == null)
            null
        else
            Gson().fromJson<Source>(value, object : TypeToken<Source>() {}.type)
    }

}