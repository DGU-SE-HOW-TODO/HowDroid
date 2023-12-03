package com.example.howdroid.presentation.home

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.howdroid.R
import com.example.howdroid.databinding.ItemInnerHomeTodoListBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.extension.ItemDiffCallback
import com.example.howdroid.util.extension.setOnSingleClickListener
import com.example.howdroid.util.extension.setVisible

class HomeTodoInnerAdapter(
    private val onClick: TodoOptionClickListener,
    private val checkToDo: TodoCheckClickListener,
) :
    ListAdapter<Home.TodoData, HomeTodoInnerAdapter.HomeTodoInnerViewHolder>(
        ItemDiffCallback<Home.TodoData>(
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

        fun onBind(todoItem: Home.TodoData) {
            binding.tvHomeTodoTitle.text = todoItem.todoName
            binding.cbHomeTodo.isChecked = todoItem.isChecked

            val textColor = when (todoItem.priority) {
                VERY_IMPORTANT -> ContextCompat.getColor(itemView.context, R.color.Orange)
                IMPORTANT -> ContextCompat.getColor(itemView.context, R.color.Green_400)
                NOT_IMPORTANT -> ContextCompat.getColor(itemView.context, R.color.Blue)
                else -> ContextCompat.getColor(itemView.context, R.color.Gray_50)
            }
            binding.viewHomeTodo.setBackgroundColor(textColor)

            if (todoItem.failtagName != null) {
                binding.tvHomeFailTag.text = String.format("# %s", todoItem.failtagName)
            } else {
                binding.tvHomeFailTag.setVisible(GONE)
            }

            if (todoItem.failtagName != null)
                binding.cbHomeTodo.isEnabled = false

            binding.root.setOnSingleClickListener {
                onClick.onOptionClick(todoItem)
            }
            binding.cbHomeTodo.setOnSingleClickListener {
                checkToDo.onCheckClick(todoItem)
            }
        }
    }

    companion object {
        private const val VERY_IMPORTANT = "매우중요"
        private const val IMPORTANT = "중요"
        private const val NOT_IMPORTANT = "중요하지 않음"
    }
}
