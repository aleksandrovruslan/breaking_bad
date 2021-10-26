package com.aleksandrov.breakingbad.presentation.episodes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.models.Episode

class EpisodesAdapter : ListAdapter<Episode, EpisodeViewHolder>(EpisodeDiff()) {

    private var _listener: OnEpisodeItemClickListener? = null

    fun setListener(listener: OnEpisodeItemClickListener) {
        _listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episode, parent, false))

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.onBind(getItem(position), _listener)
    }
}

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val season: TextView = itemView.findViewById(R.id.season)
    private val airDate: TextView = itemView.findViewById(R.id.air_date)
    private val characters: TextView = itemView.findViewById(R.id.characters)
    private val episodeTv: TextView = itemView.findViewById(R.id.episode)

    fun onBind(episode: Episode, listener: OnEpisodeItemClickListener?) {
        title.text = episode.title
        season.text = episode.season
        airDate.text = episode.air_date
        characters.text = episode.characters.joinToString(", ")
        episodeTv.text = episode.episode

        listener?.also {
            itemView.setOnClickListener { listener.onClick(episode.episode_id) }
        }
    }

}

class EpisodeDiff : DiffUtil.ItemCallback<Episode>() {
    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem.episode_id == newItem.episode_id
    }

    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }
}