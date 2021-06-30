package com.app.news.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Entity
data class Source(
    @SerializedName("articleId")
    val id: Any?,
    @SerializedName("name")
    val name: String
)
