package com.mgabor.coroutine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mgabor.coroutine.data.Beer
import com.mgabor.coroutine.databinding.BeerSecondaryItemBinding

class BeersSecondaryAdapter : ListAdapter<Beer, BeerSecondaryViewHolder>(BeersSecondaryDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerSecondaryViewHolder {
        return BeerSecondaryViewHolder(
            BeerSecondaryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BeerSecondaryViewHolder, position: Int) {
        holder.binding.beer = getItem(position)
    }
}

class BeerSecondaryViewHolder(val binding: BeerSecondaryItemBinding) : RecyclerView.ViewHolder(binding.root)

object BeersSecondaryDiffCallback : DiffUtil.ItemCallback<Beer>() {

    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem == newItem
}