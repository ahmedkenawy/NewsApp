package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.adapter.ArticleListener
import com.a7medkenawy.newsapp.adapter.NewsAdapter
import com.a7medkenawy.newsapp.db.ArticleDao
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.model.Article
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel
import com.a7medkenawy.newsapp.util.Resource
import com.a7medkenawy.newsapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_breaking_news.view.*

class BreakingNewsFragment : Fragment(), ArticleListener {

    lateinit var viewModel: NewsViewModel
    lateinit var adapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_breaking_news, container, false)

        val articleDatabase=ArticleDatabase.buildDatabase(requireContext())
        val newsRepository=NewsRepository(articleDatabase.getArticleDao())

        var viewModelFactory=ViewModelFactory(requireActivity().application,newsRepository)
        viewModel = ViewModelProvider(this,viewModelFactory)[NewsViewModel::class.java]

        adapter = NewsAdapter(this)

        with(view.rvBreakingNews){
            adapter = adapter
            layoutManager = LinearLayoutManager(activity)
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { recourse ->
            when (recourse) {
                is Resource.Success -> {

                    recourse.data?.articles?.let { adapter.setData(it) }
                }
            }

        })


        return view
    }

    override fun onArticleClicked(article: Article) {
        //go to ArticleFragment
    }


}