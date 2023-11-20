package com.example.howdroid.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.howdroid.databinding.ItemInnerHomeTodoListBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.extension.ItemDiffCallback

class HomeTodoInnerAdapter() :
    ListAdapter<Home.TodoItem, HomeTodoInnerAdapter.HomeTodoInnerViewHolder>(
        ItemDiffCallback<Home.TodoItem>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoInnerViewHolder {
        val binding =
            ItemInnerHomeTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeTodoInnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTodoInnerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HomeTodoInnerViewHolder(private val binding: ItemInnerHomeTodoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(todoItem: Home.TodoItem) {
            binding.tvHomeTodoTitle.text = todoItem.todo
            binding.cbHomeTodo.isChecked = todoItem.isChecked
        }
    }
}
