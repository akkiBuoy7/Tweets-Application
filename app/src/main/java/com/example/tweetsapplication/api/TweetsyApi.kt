package com.example.tweetsapplication.api

import com.example.tweetsapplication.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyApi {
    @GET("v3/b/66684fc7e41b4d34e401c8f2?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category:String): Response<List<TweetListItem>>

    @GET("v3/b/66684fc7e41b4d34e401c8f2?meta=false")
    @Headers("X-JSON-Path: tweets..category")
    suspend fun getCategory():Response<List<String>>
}