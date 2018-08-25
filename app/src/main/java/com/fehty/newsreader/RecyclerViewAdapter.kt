package com.fehty.newsreader

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri

class RecyclerViewAdapter(private var list: MutableList<NewsData>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private val company = view.findViewById<TextView>(R.id.company)
        private val title = view.findViewById<TextView>(R.id.title)
        private val description = view.findViewById<TextView>(R.id.description)
        private val imageView = view.findViewById<ImageView>(R.id.imageView)
        private val date = view.findViewById<TextView>(R.id.date)

        fun bind(newsData: NewsData) {
            if (newsData.articles[0].source?.name.isNullOrEmpty()) company.visibility = View.GONE
            if (newsData.articles[0].title.isNullOrEmpty()) title.visibility = View.GONE
            if (newsData.articles[0].description.isNullOrEmpty()) description.visibility = View.GONE
            if (newsData.articles[0].urlToImage.isNullOrEmpty()) imageView.visibility = View.GONE

            company.text = newsData.articles[0].source?.name
            title.text = newsData.articles[0].title
            description.text = newsData.articles[0].description
            Picasso.get().load(newsData.articles[0].urlToImage).into(imageView)
            date.text = parseDate(newsData.articles[0].publishedAt!!)
            view.setOnClickListener { sourceUrl(newsData.articles[0].url.toString()) }
        }

        @SuppressLint("SimpleDateFormat")
        fun parseDate(publishDate: String): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val date = sdf.parse(publishDate)

            val outputFormat = SimpleDateFormat("MMMM dd hh:mm a", Locale.ENGLISH)
            outputFormat.timeZone = TimeZone.getTimeZone("Russia/Moscow")

            return outputFormat.format(date)
        }

        private fun sourceUrl(url: String) {
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            view.context.startActivity(intent)
        }
    }
}