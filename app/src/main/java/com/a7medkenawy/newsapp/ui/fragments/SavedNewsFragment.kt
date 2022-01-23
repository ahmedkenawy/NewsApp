package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.adapter.ArticleListener
import com.a7medkenawy.newsapp.adapter.NewsAdapter
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.model.Article
import com.a7medkenawy.newsapp.repository.NewsRepository
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel
import com.a7medkenawy.newsapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_saved_news.view.*


class SavedNewsFragment : Fragment(), ArticleListener {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved_news, container, false)

        handleViewModel()
        newsAdapter = NewsAdapter(this)
        handleRecyclerView()
        handleSwipeMethod()

        return view
    }


    override fun onArticleClicked(article: Article) {
        val action = SavedNewsFragmentDirections.actionSavedNewsFragmentToArticleFragment(article)
        Navigation.findNavController(requireView()).navigate(action)

    }


    private fun handleSwipeMethod() {
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        try {
                            viewModel.getAllData().observe(viewLifecycleOwner, { articleList ->
                                val article = articleList[position]
                                viewModel.delete(article)
                                newsAdapter.setData(articleList)
                            })
                        } catch (ex: Exception) {
                        }
                        Toast.makeText(requireContext(), "Bad$position", Toast.LENGTH_LONG).show()
                    }
                    ItemTouchHelper.RIGHT -> {
                        Toast.makeText(requireContext(), "Good$position", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        }

        itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(view?.saved_RV)
    }

    private fun handleRecyclerView() {
        viewModel.getAllData().observe(viewLifecycleOwner, { articleList ->
            newsAdapter.setData(articleList)
        })

        view?.saved_RV?.layoutManager = LinearLayoutManager(requireContext())
        view?.saved_RV?.adapter = newsAdapter

    }

    private fun handleViewModel() {
        val articleDatabase = ArticleDatabase.buildDatabase(requireContext())
        val newsRepository = NewsRepository(articleDatabase.getArticleDao())

        var viewModelFactory = ViewModelFactory(requireActivity().application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsViewModel::class.java]
    }

}