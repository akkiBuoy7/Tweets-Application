package com.example.tweetsapplication.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsapplication.models.TweetListItem
import com.example.tweetsapplication.repository.TweetRepository
import com.example.tweetsapplication.routes.NavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Inject repository within constructor
@HiltViewModel
class TweetListingViewModel @Inject constructor(
	private val repository : TweetRepository ,
	savedStateHandle : SavedStateHandle
) : ViewModel() {

	val tweets : StateFlow<List<TweetListItem>>
		get() = repository.tweets

	init {
		val category =
			savedStateHandle.get<String>(NavArgs.NAV_CATEGORY) ?: NavArgs.NAV_ANDROID
		viewModelScope.launch {
			repository.getTweets(category)
		}
	}
}