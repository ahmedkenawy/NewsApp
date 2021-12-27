package com.a7medkenawy.newsapp.repository

import com.a7medkenawy.newsapp.api.RetrofitInstance
import com.a7medkenawy.newsapp.db.ArticleDao
import com.a7medkenawy.newsapp.db.ArticleDatabase

class NewsRepository(val db:ArticleDao) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews (question:String,pageNumber: Int)=
        RetrofitInstance.api.searchNews(question,pageNumber)

}