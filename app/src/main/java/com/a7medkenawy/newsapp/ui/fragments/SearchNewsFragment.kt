package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel
import com.a7medkenawy.newsapp.viewmodel.ViewModelFactory

class SearchNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_news, container, false)
        val articleDatabase= ArticleDatabase.buildDatabase(requireContext())
        val newsRepository=NewsRepository(articleDatabase.getArticleDao())

        var viewModelFactory= ViewModelFactory(requireActivity().application,newsRepository)
        viewModel = ViewModelProvider(this,viewModelFactory)[NewsViewModel::class.java]
        return view
    }


}