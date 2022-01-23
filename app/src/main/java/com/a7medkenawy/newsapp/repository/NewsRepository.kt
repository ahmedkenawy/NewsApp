package com.a7medkenawy.newsapp.repository

import com.a7medkenawy.newsapp.api.RetrofitInstance
import com.a7medkenawy.newsapp.db.ArticleDao
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.model.Article

class NewsRepository(val db: ArticleDao) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(question: String, pageNumber: Int) =
        RetrofitInstance.api.searchNews(question, pageNumber)


    suspend fun insert(article: Article) = db.insert(article)

    fun getAllData() = db.getAllArticles()

    suspend fun delete(article: Article) = db.delete(article)

}