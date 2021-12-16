package com.a7medkenawy.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.a7medkenawy.newsapp.R
import com.a7medkenawy.newsapp.model.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter(var listener: ArticleListener) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var oldList = emptyList<Article>()

    fun setData(newList: List<Article>) {
        val diffResult = DiffUtil.calculateDiff(NewsDiffUtil(oldList, newList))
        oldList = newList;
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_preview, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = oldList[position]

        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(urlToImage)
            tv_author.text = article.author
            tv_publish_date.text = article.publishedAt
            tv_content.text = article.content
            tv_description.text = article.description
            setOnClickListener {
                listener.onArticleClicked(article)
            }
        }
    }

    override fun getItemCount() = oldList.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}