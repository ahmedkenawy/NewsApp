package com.a7medkenawy.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.a7medkenawy.newsapp.model.Article

@Dao
interface ArticleDao {

    @Insert
    suspend fun insert(article: Article)


    @Query("select * from articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun delete(article: Article)
}