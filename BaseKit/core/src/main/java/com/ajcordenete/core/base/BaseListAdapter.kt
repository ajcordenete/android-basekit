package com.ajcordenete.core.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

abstract class BaseListAdapter<T, VH : BaseListAdapter.BaseViewViewHolder<T>>(
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffUtil) {

    protected val items: MutableList<T> = mutableListOf()

    open class BaseViewViewHolder<T>(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        var itemPosition: Int = -1

        open fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    open fun updateItems(items: List<T>, clearList: Boolean = false) {
        if (clearList) {
            this.items.clear()
        }
        this.items.addAll(items)
        submitList(this.items.toMutableList())
    }

    open fun addAllItems(items: List<T>) {
        val list: MutableList<T> = currentList.toMutableList()
        list.addAll(items)

        submitList(list)
    }

    open fun addSingleItem(item: T) {
        this.items.add(item)
        submitList(this.items.toMutableList())
    }

    open fun addSingleItem(position: Int, item: T) {
        this.items.add(position, item)
        submitList(this.items.toMutableList())
    }

    open fun moveItem(fromPosition: Int, toPosition: Int) {
        val list: MutableList<T> = currentList.toMutableList()
        Collections.swap(list, fromPosition, toPosition)
        submitList(list)
    }

    open fun removeSingleItem(item: T) {
        this.items.remove(item)
        submitList(this.items.toMutableList())
    }
}