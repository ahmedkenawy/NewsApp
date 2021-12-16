package com.a7medkenawy.newsapp.api

import com.a7medkenawy.newsapp.model.NewsResponse
import com.a7medkenawy.newsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        country: String = "eg",
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY,

    ): Response<NewsResponse>


    @GET("v2/top-headlines")
    suspend fun searchNews(
        @Query("q")
        question: String,
        @Query("page")
        page: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY,

    ): Response<NewsResponse>

}