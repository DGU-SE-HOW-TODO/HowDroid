package com.example.howdroid.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.howdroid.databinding.ItemOuterHomeTodoListBinding
import com.example.howdroid.domain.model.home.Home
import com.example.howdroid.util.extension.ItemDiffCallback
import com.example.howdroid.util.extension.setOnSingleClickListener

class HomeTodoOuterAdapter(
    private val onInnerItemClick: TodoOptionClickListener,
    private val onInnerToDoCheck: TodoCheckClickListener,
    private val moveToAddToDo: (Int) -> Unit,
) :
    ListAdapter<Home.TodoCategoryData, HomeTodoOuterAdapter.HomeTodoOuterViewHolder>(
        ItemDiffCallback<Home.TodoCategoryData>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new },
        ),
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTodoOuterViewHolder {
        val binding =
            ItemOuterHomeTodoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeTodoOuterViewHolder(binding, onInnerItemClick, onInnerToDoCheck)
    }

    override fun onBindViewHolder(holder: HomeTodoOuterViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class HomeTodoOuterViewHolder(
        private val binding: ItemOuterHomeTodoListBinding,
        onInnerItemClick: TodoOptionClickListener,
        onInnerToDoCheck: TodoCheckClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rvInnerHomeTodoList.adapter =
                HomeTodoInnerAdapter(onInnerItemClick, onInnerToDoCheck)
        }

        fun onBind(
            home: Home.TodoCategoryData,
        ) {
            binding.tvHomeCategoryTitle.text = home.todoCategory
            (binding.rvInnerHomeTodoList.adapter as HomeTodoInnerAdapter).submitList(
                home.todoData,
            )
            binding.tvHomeCategoryAdd.setOnSingleClickListener {
                moveToAddToDo(home.todoCategoryId)
            }
        }
    }
}
