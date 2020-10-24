package com.akaf.batmanapplication.ui.main.adapter.genreList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akaf.batmanapplication.App
import com.akaf.batmanapplication.R
import java.util.*

class AdapterGenre : RecyclerView.Adapter<ViewHolderGenre>() {

    private var list: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderGenre =
        ViewHolderGenre(
            LayoutInflater.from(App.context).inflate(R.layout.genre_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolderGenre, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    fun updateList(genreList: List<String>) {
        list.addAll(genreList)
        notifyDataSetChanged()
    }
}