package com.fehty.newsreader

import retrofit2.Call
import retrofit2.http.GET

interface ApiMethods {

    @GET("/v2/top-headlines?country=us&category=business&apiKey=7c17de7562904acd8531a24341e31432")
    fun getBusiness(): Call<NewsData>

    @GET("/v2/everything?q=bitcoin&sortBy=publishedAt&apiKey=7c17de7562904acd8531a24341e31432")
    fun getBitcoin(): Call<NewsData>

    @GET("/v2/top-headlines?sources=techcrunch&apiKey=7c17de7562904acd8531a24341e31432")
    fun getTechCrunch(): Call<NewsData>

    @GET("v2/everything?domains=wsj.com&apiKey=7c17de7562904acd8531a24341e31432")
    fun getTheWallStreetJournal(): Call<NewsData>
}