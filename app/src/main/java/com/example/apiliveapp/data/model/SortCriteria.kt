package com.example.apiliveapp.data.model

//relevancy, popularity, publishedAt
enum class SortCriteria(val text: String) {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED("publishedAt"),
}