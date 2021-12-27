package com.a7medkenawy.newsapp.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "articles")
@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id:@RawValue Int?=null,
    @SerializedName("author")
    val author:@RawValue String?,
    @SerializedName("content")
    val content:@RawValue String?,
    @SerializedName("description")
    val description:@RawValue String?,
    @SerializedName("publishedAt")
    val publishedAt:@RawValue String?,
    @SerializedName("source")
    val source:@RawValue Source?,
    @SerializedName("title")
    val title:@RawValue String?,
    @SerializedName("url")
    val url:@RawValue String?,
    @SerializedName("urlToImage")
    val urlToImage:@RawValue String?

):Parcelable