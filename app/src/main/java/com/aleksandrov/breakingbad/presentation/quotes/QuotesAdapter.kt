package com.aleksandrov.breakingbad.presentation.quotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.domain.models.Quote

class QuotesAdapter : ListAdapter<Quote, QuotesViewHolder>(QuotesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder =
        QuotesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote, parent, false))

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

}

class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val author: TextView = itemView.findViewById(R.id.author)
    private val quoteTv: TextView = itemView.findViewById(R.id.quote)

    fun onBind(quote: Quote) {
        author.text = quote.author
        quoteTv.text = quote.quote
    }

}

class QuotesDiff : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.quote_id == newItem.quote_id
    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem == newItem
    }
}