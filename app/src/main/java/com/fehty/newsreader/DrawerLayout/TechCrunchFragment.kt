package com.fehty.newsreader.DrawerLayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fehty.newsreader.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_all_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TechCrunchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMethods::class.java)

    val list = mutableListOf<NewsData>()
    val adapter = RecyclerViewAdapter(list)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        getTechCrunchData()

        swipeToRefresh.setColorSchemeColors(ContextCompat.getColor(activity!!, R.color.colorPrimary))

        activity!!.toolbar.setOnClickListener { recyclerView.scrollToPosition(0) }

        swipeToRefresh.setOnRefreshListener {
            list.clear()
            adapter.notifyDataSetChanged()
            getTechCrunchData()
        }
    }

    private fun getTechCrunchData() {
        retrofit.getTechCrunch().enqueue(object : Callback<NewsData> {
            override fun onFailure(call: Call<NewsData>, t: Throwable) = Unit
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val apiResponse = response.body()
                apiResponse?.articles?.forEach {
                    list.add(NewsData(listOf(Article(it.source, it.title, it.description, it.url, it.urlToImage, it.publishedAt))))
                }
                adapter.notifyDataSetChanged()
                swipeToRefresh.isRefreshing = false
            }
        })
    }
}
