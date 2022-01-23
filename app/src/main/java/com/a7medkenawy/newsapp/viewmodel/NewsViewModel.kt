package com.a7medkenawy.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.a7medkenawy.newsapp.model.Article
import com.a7medkenawy.newsapp.model.NewsResponse
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(application: Application, val newsRepository: NewsRepository) :
    AndroidViewModel(application) {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingPageNumber = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPageNumber = 1

    init {
        getBreakingNews("us")
    }


    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        breakingNews.postValue(
            handleBreakingNewsResponse(
                newsRepository.getBreakingNews(
                    countryCode,
                    breakingPageNumber
                )
            )
        )
    }


    fun searchNews(question: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        searchNews.postValue(
            handleSearchNews(
                newsRepository.searchNews(
                    question,
                    searchPageNumber
                )
            )
        )
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }


    fun insert(article: Article) = viewModelScope.launch {
        newsRepository.insert(article)
    }

    fun delete(article: Article) = viewModelScope.launch {
        newsRepository.delete(article)
    }


    fun getAllData() = newsRepository.getAllData()


}