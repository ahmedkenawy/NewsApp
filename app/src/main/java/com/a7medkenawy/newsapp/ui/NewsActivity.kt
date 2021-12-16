package com.a7medkenawy.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.db.ArticleDatabase
import com.a7medkenawy.newsapp.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        bottomNavigationView.setupWithNavController(Navigation.findNavController(this,R.id.fragmentContainerView))
    }
}