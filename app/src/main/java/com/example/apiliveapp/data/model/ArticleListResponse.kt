package com.example.apiliveapp.data.model

import com.squareup.moshi.Json

data class ArticleListResponse (

    val status : String,
    val totalResults : Int,
    val articles : List<Article>

)


data class Article(
    val author : String?,
    val title : String?,
    val description : String?,
    val url: String?,

    @Json(name = "urlToImage")
    val imageUrl: String?,

    val content: String?,

)