package com.a7medkenawy.newsapp.adapter

import com.a7medkenawy.newsapp.model.Article

interface ArticleListener {
    fun onArticleClicked(article: Article)
}