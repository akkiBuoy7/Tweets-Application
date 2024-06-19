package com.example.tweetsapplication.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsScreen(tweet:String) {
	Text(text = tweet, modifier = Modifier.fillMaxSize())
}