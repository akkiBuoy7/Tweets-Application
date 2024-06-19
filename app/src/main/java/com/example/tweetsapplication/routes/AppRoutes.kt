package com.example.tweetsapplication.routes

//class AppRoutes {
//    companion object {
//        const val CATEGORY_SCREEN = "category_screen"
//        const val TWEETS_SCREEN = "tweets_screen"
//        const val DETAILS_SCREEN = "details_screen"
//    }
//
//}

// It contains route names to all three screens
sealed class AppRoutes(val route : String) {

	data object CATEGORY_SCREEN : AppRoutes("category_screen")
	data object TWEETS_SCREEN : AppRoutes("tweets_screen")
	data object DETAILS_SCREEN : AppRoutes("details_screen")
}

class NavArgs {
	companion object {

		const val NAV_CATEGORY = "nav_category"
		const val NAV_ANDROID = "android"
		const val NAV_TWEET = "tweet"
	}
}