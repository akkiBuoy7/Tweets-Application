package com.example.tweetsapplication.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.tweetsapplication.models.TweetListItem
import com.example.tweetsapplication.routes.AppRoutes
import com.example.tweetsapplication.viewModels.TweetListingViewModel

@Composable
fun TweetListingScreen(
	navController : NavHostController ,
) {
	val tweetListingViewModel : TweetListingViewModel = hiltViewModel()
	val tweets : State<List<TweetListItem>> =
		tweetListingViewModel.tweets.collectAsState()

	if (tweets.value.isEmpty()) {
		Box(
			modifier = Modifier.fillMaxSize() ,
			contentAlignment = Alignment.Center
		) {
			CircularProgressIndicator(
				color = Color.White ,
				strokeWidth = 2.dp ,
				modifier = Modifier.size(30.dp)
			)
		}

	} else {
		LazyColumn {
			items(tweets.value) {

				DetailItem(tweet = it.tweet , navController)
			}
		}

	}

}

@Composable
fun DetailItem(
	tweet : String ,
	navController : NavHostController ,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
			.clickable {
				navController.navigate("${AppRoutes.DETAILS_SCREEN.route}/${tweet}")
			} ,
		border = BorderStroke(1.dp , Color(0xFFCCCCCC))
	) {

		Text(
			text = tweet ,
			modifier = Modifier.padding(16.dp) ,
			style = MaterialTheme.typography.bodySmall
		)
	}
}