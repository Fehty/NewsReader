package com.fehty.newsreader

data class NewsData(
        val articles: List<Article>
)

data class Article(
        val source: Source?,
        val title: String?,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String?
)

data class Source(
        val id: String,
        val name: String
)