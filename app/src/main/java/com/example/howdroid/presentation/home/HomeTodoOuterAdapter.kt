package com.example.howdroid.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.howdroid.databinding.ItemOuterHomeTodoListBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.extension.ItemDiffCallback

class HomeTodoOuterAdapter() :
    ListAdapter<Home, HomeTodoOuterAdapter.HomeTodoOuterViewHolder>(
        ItemDiffCallback<Home>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoOuterViewHolder {
        val binding =
            ItemOuterHomeTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeTodoOuterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTodoOuterViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HomeTodoOuterViewHolder(private val binding: ItemOuterHomeTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(home: Home) {
            binding.tvHomeCategoryTitle.text =
                home.todoCategoryData[absoluteAdapterPosition].todoCategory

            binding.rvInnerHomeTodoList.adapter = HomeTodoInnerAdapter().apply {
                submitList(home.todoCategoryData[absoluteAdapterPosition].todoData)
            }
        }
    }
}
