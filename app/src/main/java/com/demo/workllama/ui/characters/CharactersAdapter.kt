package com.demo.workllama.ui.characters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.demo.workllama.R
import com.demo.workllama.data.entities.Characters
import com.demo.workllama.databinding.ItemCharacterBinding
import com.demo.workllama.utils.Constant

class CharactersAdapter(private val listener: CharacterItemListener) :
    RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<Characters>()

    fun setItems(items: ArrayList<Characters>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener, parent.context)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])
}

class CharacterViewHolder(
    private val itemBinding: ItemCharacterBinding,
    private val listener: CharactersAdapter.CharacterItemListener,
    context: Context
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var characters: Characters

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Characters) {
        this.characters = item
        itemBinding.name.text = item.name
        itemBinding.phNo.text = item.phone
        if (item.isStarred > 0)
            itemBinding.heart.setBackgroundResource(R.drawable.ic_heart_red)
        else itemBinding.heart.setBackgroundResource(R.drawable.ic_heart)

        Glide.with(itemBinding.root)
            .load(Constant.BASE_URL_IMAGE + item.thumbnail)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(characters.id)
    }
}

