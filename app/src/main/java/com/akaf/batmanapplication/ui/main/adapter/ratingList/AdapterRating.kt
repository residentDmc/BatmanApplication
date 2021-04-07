package com.akaf.batmanapplication.ui.main.adapter.ratingList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akaf.batmanapplication.utils.application.App
import com.akaf.batmanapplication.R
import com.akaf.batmanapplication.data.model.moviedetail.Rating
import java.util.*

class AdapterRating : RecyclerView.Adapter<ViewHolderRating>() {

    private val list: ArrayList<Rating> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRating = ViewHolderRating(
        LayoutInflater.from(App.context).inflate(R.layout.rating_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolderRating, position: Int) =holder.bind(list[position])

    override fun getItemCount(): Int =list.size

    fun updateList(ratings: List<Rating>) {
        list.clear()
        list.addAll(ratings)
        notifyDataSetChanged()
    }
}