package com.example.breweryapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.breweryapp.databinding.BreweryItemLayoutBinding
import com.example.breweryapp.domain.entities.BreweryModel

class BreweryAdapter : ListAdapter<BreweryModel, BreweryAdapter.BreweryViewHolder>(DiffCallback()) {

    class BreweryViewHolder(private val binding: BreweryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BreweryModel) {
            binding.apply {
                brewery = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreweryViewHolder {
        return BreweryViewHolder(
            BreweryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BreweryViewHolder, position: Int) {
        val brewery = getItem(position)
        holder.bind(brewery)
    }
}

class DiffCallback : DiffUtil.ItemCallback<BreweryModel>() {
    override fun areItemsTheSame(oldItem: BreweryModel, newItem: BreweryModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BreweryModel, newItem: BreweryModel): Boolean {
        return oldItem == newItem
    }
}