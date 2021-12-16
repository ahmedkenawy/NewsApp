package com.a7medkenawy.newsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.viewmodel.NewsViewModel


class SearchNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_news, container, false)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        return view
    }


}