package com.example.apiliveapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apiliveapp.data.model.Article
import com.example.apiliveapp.data.remote.NewsApi

class Repository {

    private val _newsList = MutableLiveData<List<Article>>()
    val newsList : LiveData<List<Article>>
        get() = _newsList

    suspend fun searchNews(searchTerm: String){
        val result = NewsApi.apiService.searchNews(
            searchTerm = searchTerm,
        )
        _newsList.postValue(result.articles)
    }

    suspend fun loadData() {
        val result = NewsApi.apiService.getTopNews()
        //Repository entpackt die Daten in das Format welches für das UI richtig ist
        _newsList.postValue(result.articles)
    }

}