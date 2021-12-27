package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.adapter.ArticleListener
import com.a7medkenawy.newsapp.adapter.NewsAdapter
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.model.Article
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.util.Resource
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel
import com.a7medkenawy.newsapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_breaking_news.view.*
import kotlinx.android.synthetic.main.fragment_saved_news.*
import kotlinx.android.synthetic.main.fragment_saved_news.view.*
import kotlinx.android.synthetic.main.fragment_search_news.view.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(), ArticleListener {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_news, container, false)

        val articleDatabase = ArticleDatabase.buildDatabase(requireContext())
        val newsRepository = NewsRepository(articleDatabase.getArticleDao())
        var viewModelFactory = ViewModelFactory(requireActivity().application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]

//        try {
        newsAdapter = NewsAdapter(this)
        view.rvSearchNews.adapter = newsAdapter
        view.rvSearchNews.layoutManager = LinearLayoutManager(activity)
//        }catch (ex:Exception){}


//        var jop:Job?=null
//        view.etSearch.addTextChangedListener {editable->
//            jop?.cancel()
//            jop= MainScope().launch {
//                delay(500)
//                editable?.let {
//                    if (editable.toString().isNotEmpty()){
//                        viewModel.searchNews(editable.toString())
//                    }
//                }
//            }
//        }


        view.etSearch.addTextChangedListener { editable ->
            lifecycleScope.launch {
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }

            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { resource ->

            when (resource) {
                is Resource.Success -> {
                    resource.data?.articles?.let {
                        newsAdapter.setData(it)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                }
            }

        })

        return view
    }

    override fun onArticleClicked(article: Article) {
        val action = SearchNewsFragmentDirections.actionSearchNewsFragmentToArticleFragment(article)
        Navigation.findNavController(requireView()).navigate(action)
    }


}