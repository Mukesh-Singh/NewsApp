package com.app.news.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.news.R
import com.app.news.data.model.Article
import com.app.news.databinding.RowNewsBinding
import com.app.news.utils.formatTo
import com.app.news.utils.toDate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Mukesh on 30-Jun-21.
 */
class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var listData= mutableListOf<Article>()
    var itemClickListener: ItemClickListener? = null

    interface ItemClickListener{
        fun onItemClicked(item: Article, binding: RowNewsBinding)
    }

    fun updateList(newList: List<Article>){
        listData.let {
            it.clear()
            listData.addAll(newList)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val binding = RowNewsBinding.inflate(inflater,parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }


    inner class NewsViewHolder(val binding: RowNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(article: Article){
            binding.title.text = article.title
            val date = (article.publishedAt)?.toDate()?.formatTo("dd MMM yyyy")
            val sourceAndTime = "${article.source?.name} on $date "
            binding.sourceAndTime.text = sourceAndTime
            binding.description.text = article.description
            binding.title.transitionName= "Title-$adapterPosition"
            Glide.with(binding.imageView)
                .load(article.urlToImage)
                .placeholder(R.drawable.place_holder_image)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .into(binding.imageView)
            binding.itemLayout.setOnClickListener {
                itemClickListener.let {
                    it?.onItemClicked(article,binding)
                }
            }
        }
    }
}