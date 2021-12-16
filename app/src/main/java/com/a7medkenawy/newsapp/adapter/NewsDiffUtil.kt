package com.a7medkenawy.newsapp.adapter

import androidx.recyclerview.widget.DiffUtil
import com.a7medkenawy.newsapp.model.Article

class NewsDiffUtil(var oldList: List<Article>, var newList: List<Article>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (
                oldList[oldItemPosition].id == newList[newItemPosition].id
                        && oldList[oldItemPosition].author == newList[newItemPosition].author
                        && oldList[oldItemPosition].source?.name == newList[newItemPosition].source?.name
                )

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

}