package com.example.tweetsapplication.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsapplication.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository : TweetRepository) :
		ViewModel() {

	val categories : StateFlow<List<String>> // get the categories state from repository
		get() = repository.categories

	// Need to get api call on screen appearance so called within init
	init {
		viewModelScope.launch {
			repository.getCategories() // call the method of the repository
		}
	}
}