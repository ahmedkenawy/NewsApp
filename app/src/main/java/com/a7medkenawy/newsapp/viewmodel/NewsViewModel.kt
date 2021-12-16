package com.a7medkenawy.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.a7medkenawy.newsapp.model.NewsResponse
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(application: Application, val newsRepository: NewsRepository) :
    AndroidViewModel(application) {


    init {
        getBreakingNews("eg")
    }

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingPageNumber = 1

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

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}