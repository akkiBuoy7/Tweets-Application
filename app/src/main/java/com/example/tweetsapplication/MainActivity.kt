package com.example.tweetsapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import com.example.tweetsapplication.api.TweetsyApi
import com.example.tweetsapplication.ui.theme.TweetsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsyApi: TweetsyApi
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            val response = tweetsyApi.getCategory()
            Log.d("My App", "onCreate: ${response.body()!!.distinct()}")

        }

        setContent {
            TweetsApplicationTheme {
                Text(text = "AKASH", fontSize = 50.sp)

            }
        }
    }
}
