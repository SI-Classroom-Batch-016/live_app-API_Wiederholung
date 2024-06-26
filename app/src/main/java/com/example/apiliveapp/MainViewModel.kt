package com.example.apiliveapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiliveapp.data.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()
    val topNews = repository.newsList

    init {
        viewModelScope.launch {
            repository.loadData()
        }
    }

    fun searchNews(searchTerm: String) {
        viewModelScope.launch {
            if (searchTerm.isBlank()) {
                repository.loadData()
            } else {
                repository.searchNews(searchTerm)
            }
        }
    }


}