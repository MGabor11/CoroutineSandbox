package com.mgabor.coroutine.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mgabor.coroutine.data.Beer
import com.mgabor.coroutine.databinding.BeerItemBinding

class BeersAdapter : ListAdapter<Beer, BeerViewHolder>(BeersDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        return BeerViewHolder(
            BeerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.binding.beer = getItem(position)
    }
}

class BeerViewHolder(val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root)

object BeersDiffCallback : DiffUtil.ItemCallback<Beer>() {

    override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean = oldItem == newItem
}