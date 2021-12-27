package com.a7medkenawy.newsapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a7medkenawy.newsapp.repository.NewsRepository
import java.lang.Exception
import java.lang.IllegalArgumentException

class ViewModelFactory(private var application: Application,val newsRepository: NewsRepository) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(application,newsRepository) as T
        }
        throw IllegalArgumentException("Error Here")
    }
}