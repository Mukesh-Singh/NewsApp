package com.app.news.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.news.data.model.Article
import io.reactivex.Completable

/**
 * Created by Mukesh on 30-Jun-21.
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Article>)

    @Query("DELETE FROM article_table")
    fun deleteAllArticles()

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): LiveData<List<Article>>

    @get:Query("SELECT * FROM article_table")
    val all: List<Article>
}