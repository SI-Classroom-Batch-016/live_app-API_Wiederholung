package com.example.apiliveapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.apiliveapp.data.model.Article
import com.example.apiliveapp.databinding.NewsItemBinding
import com.example.apiliveapp.misc.Constants

class NewsAdapter(
    val dataset: List<Article>
) : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.newsTV.text = item.title

        item.imageUrl?.let { url ->
            //Dieser Code wird nur ausgeführt wenn das Bild existiert(ungleich null)
            holder.binding.newsIV.load(url) {
                crossfade(true)
            }
        }

    }
}