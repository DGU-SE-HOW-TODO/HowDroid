package com.example.howdroid.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.howdroid.R
import com.example.howdroid.databinding.ItemInnerHomeTodoListBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.extension.ItemDiffCallback
import com.example.howdroid.util.extension.setOnSingleClickListener

class HomeTodoInnerAdapter(
    private val onClick: TodoOptionClickListener,
) :
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

            val textColor = when (todoItem.priority) {
                VERY_IMPORTANT -> ContextCompat.getColor(itemView.context, R.color.Orange)
                IMPORTANT -> ContextCompat.getColor(itemView.context, R.color.Green_400)
                NOT_IMPORTANT -> ContextCompat.getColor(itemView.context, R.color.Blue)
                else -> ContextCompat.getColor(itemView.context, R.color.Gray_50)
            }
            binding.viewHomeTodo.setBackgroundColor(textColor)

            binding.root.setOnSingleClickListener {
                onClick.onOptionClick(todoItem)
            }
        }
    }

    companion object {
        private const val VERY_IMPORTANT = "매우중요"
        private const val IMPORTANT = "중요"
        private const val NOT_IMPORTANT = "중요하지 않음"
    }
}
