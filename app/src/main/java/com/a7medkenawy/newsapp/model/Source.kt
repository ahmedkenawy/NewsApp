package com.a7medkenawy.newsapp.model


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.RawValue

data class Source(
    @SerializedName("id")
    val id:@RawValue Any?,
    @SerializedName("name")
    val name:@RawValue String?
)