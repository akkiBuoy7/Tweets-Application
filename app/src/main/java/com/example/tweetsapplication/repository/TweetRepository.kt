package com.example.tweetsapplication.repository

import com.example.tweetsapplication.api.TweetsyApi
import com.example.tweetsapplication.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// Inject TweetsyApi within constructor
class TweetRepository @Inject constructor(private val tweetsyApi: TweetsyApi) {

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())

    val tweets: StateFlow<List<TweetListItem>> // get mutable state flow into state flow
        get() = _tweets

    private val _categories = MutableStateFlow<List<String>>(emptyList())

    val categories: StateFlow<List<String>>
        get() = _categories

    suspend fun getTweets(category: String) {
        val result = tweetsyApi.getTweets("tweets[?(@.category==\"$category\")]") // call API
        if (result.isSuccessful && result.body() != null) {
            _tweets.emit(result.body()!!) // Emit Mutable state flow
        }
    }

    suspend fun getCategories() {
        val result = tweetsyApi.getCategory()
        if (result.isSuccessful && result.body() != null) {
            _categories.emit(result.body()!!)
        }
    }
}