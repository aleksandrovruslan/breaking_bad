package com.aleksandrov.breakingbad.presentation.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aleksandrov.breakingbad.R
import com.aleksandrov.breakingbad.models.Character
import com.bumptech.glide.Glide

class CharactersAdapter : ListAdapter<Character, CharacterItemViewHolder>(CharactersDiff()) {

    private var listener: OnItemClickListener? = null

    fun setListener(onItemClickListener: OnItemClickListener) {
        listener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder =
        CharacterItemViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false))

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.onBind(getItem(position), listener)
    }

}

class CharacterItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val img: ImageView = itemView.findViewById(R.id.img)
    private val name: TextView = itemView.findViewById(R.id.name)
    private val birthday: TextView = itemView.findViewById(R.id.birthday)
    private val occupation: TextView = itemView.findViewById(R.id.occupation)
    private val status: TextView = itemView.findViewById(R.id.status)
    private val nickname: TextView = itemView.findViewById(R.id.nickname)
    private val appearance: TextView = itemView.findViewById(R.id.appearance)
    private val portrayed: TextView = itemView.findViewById(R.id.portrayed)
    private val betterCallSaulAppearancePrev: TextView =
        itemView.findViewById(R.id.better_call_saul_appearance_prev)
    private val betterCallSaulAppearance: TextView =
        itemView.findViewById(R.id.better_call_saul_appearance)

    fun onBind(character: Character, listener: OnItemClickListener?) {
        Glide.with(itemView)
            .load(character.img)
            .into(img)
        name.text = character.name
        birthday.text = character.birthday
        occupation.text = character.occupation.joinToString(", ")
        status.text = character.status
        nickname.text = character.nickname
        appearance.text = character.appearance.joinToString(", ")
        portrayed.text = character.portrayed
        character.better_call_saul_appearance.also {
            if (it.isNotEmpty()) {
                betterCallSaulAppearancePrev.visibility = View.VISIBLE
                betterCallSaulAppearance.visibility = View.VISIBLE
                betterCallSaulAppearance.text = it.joinToString(", ")
            }
        }
        itemView.setOnClickListener { listener?.also { listener.onClick(character.char_id) } }
    }

}

class CharactersDiff : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.char_id == newItem.char_id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}