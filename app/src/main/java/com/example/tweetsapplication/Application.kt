package com.example.tweetsapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsapplication.routes.AppRoutes
import com.example.tweetsapplication.routes.NavArgs
import com.example.tweetsapplication.screens.CategoryScreen
import com.example.tweetsapplication.screens.DetailsScreen
import com.example.tweetsapplication.screens.TweetListingScreen

@Composable
fun App() {
	val navController = rememberNavController()

	NavHost(
		navController = navController ,
		startDestination = AppRoutes.CATEGORY_SCREEN.route
	) {
		composable(route = AppRoutes.CATEGORY_SCREEN.route) {
			CategoryScreen {
				// using callback to get the data from the screen and using navigate
				navController.navigate("${AppRoutes.TWEETS_SCREEN.route}/${it}")
			}
		}

		composable(route = "${AppRoutes.TWEETS_SCREEN.route}/{${NavArgs.NAV_CATEGORY}}" ,
		           arguments = listOf(
			           navArgument(NavArgs.NAV_CATEGORY) {
				           type = NavType.StringType
			           }

		           )
		) {
			// sending navController to the screen for navigate
			TweetListingScreen(navController)
		}

		composable(route = AppRoutes.DETAILS_SCREEN.route + "/{${NavArgs.NAV_TWEET}}" ,
		           arguments = listOf(
			           navArgument(NavArgs.NAV_TWEET) {
				           type = NavType.StringType
			           }

		           )
		) {
			// getting nav args sent from TWEETS_SCREEN from backStack of DetailsScreen
			val tweet = it.arguments?.getString(NavArgs.NAV_TWEET)
			DetailsScreen(tweet = tweet!!)
		}

	}

}