package com.akaf.batmanapplication.ui.main.adapter.ratingList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.akaf.batmanapplication.data.model.moviedetail.Rating
import kotlinx.android.synthetic.main.rating_item.view.*

class ViewHolderRating (itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(rating: Rating){
        itemView.txt_rating_title.text = rating.source
        itemView.txt_rating_value.text = rating.value
    }
}