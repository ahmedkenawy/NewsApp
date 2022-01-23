package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel
import com.a7medkenawy.newsapp.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_article.view.*


class ArticleFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    val navArgs: ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        val articleDatabase = ArticleDatabase.buildDatabase(requireContext())
        val newsRepository = NewsRepository(articleDatabase.getArticleDao())

        var viewModelFactory = ViewModelFactory(requireActivity().application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

        var article = navArgs.currentArticle

        view.newsWebView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        view.fab.setOnClickListener {
            viewModel.insert(article)
            Toast.makeText(requireContext(),"Article Added Successfully",Toast.LENGTH_LONG).show()
        }



        return view
    }

}