package com.example.tweetsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetsapplication.routes.AppRoutes
import com.example.tweetsapplication.routes.NavArgs
import com.example.tweetsapplication.screens.CategoryScreen
import com.example.tweetsapplication.screens.TweetListingScreen
import com.example.tweetsapplication.ui.theme.TweetsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TweetsApplicationTheme {
				Scaffold(
					topBar = {
						TopAppBar(
							title = {
								Text(text = "Tweetsy")
							} , colors = TopAppBarColors(
								containerColor = Color.Black ,
								titleContentColor = Color.White ,
								navigationIconContentColor = Color.Transparent ,
								scrolledContainerColor = Color.Transparent ,
								actionIconContentColor = Color.Transparent
							)
						)
					}
				) {
					Box(Modifier.padding(it)) {
						App()
					}
				}

			}
		}
	}
}

@Composable
fun App() {
	val navController = rememberNavController()
	AppRoutes

	NavHost(
		navController = navController ,
		startDestination = AppRoutes.CATEGORY_SCREEN
	) {
		composable(route = AppRoutes.CATEGORY_SCREEN) {
			CategoryScreen {
				navController.navigate("${AppRoutes.TWEETS_SCREEN}/${it}")
			}
		}
		composable(route = "${AppRoutes.TWEETS_SCREEN}/{${NavArgs.NAV_CATEGORY}}" ,
		           arguments = listOf(
			           navArgument(NavArgs.NAV_CATEGORY) {
				           type = NavType.StringType
			           }

		           )
		) {
			TweetListingScreen()
		}

	}

}
