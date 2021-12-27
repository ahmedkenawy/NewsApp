package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.view.*


class ArticleFragment : Fragment() {

    val navArgs: ArticleFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        var article=navArgs.currentArticle

        view.newsWebView.apply {
            webViewClient= WebViewClient()
            article.url?.let { loadUrl(it) }
        }
        return view
    }

}