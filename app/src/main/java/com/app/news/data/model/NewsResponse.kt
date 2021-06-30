package com.app.news.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mukesh on 30-Jun-21.
 */
data class NewsResponse(
    @SerializedName("articles")
    val articles: MutableList<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)