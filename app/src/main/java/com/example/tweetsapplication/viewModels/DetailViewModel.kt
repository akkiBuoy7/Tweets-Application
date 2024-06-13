package com.example.tweetsapplication.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsapplication.models.TweetListItem
import com.example.tweetsapplication.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


// Inject repository within constructor
@HiltViewModel
class DetailViewModel @Inject constructor(val repository: TweetRepository) : ViewModel() {
    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init {
        viewModelScope.launch() {
            repository.getTweets("")
        }
    }
}