package com.erbe.nowinandroid.core.common.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

abstract class BaseAdapter<T : Any, VB : ViewBinding>(
    private val inflaterFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val onItemBind: (T, VB, View) -> Unit
) : ListAdapter<T, BaseAdapter.BaseViewHolder<T, VB>>(BaseItemCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val binding = inflaterFactory(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return BaseViewHolder(view, binding, onItemBind)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class BaseViewHolder<T : Any, VB : ViewBinding>(
        view: View,
        private val binding: VB,
        private val onItemBind: (T, VB, View) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: T) {
            onItemBind(item, binding, itemView)
        }
    }

    class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.toString() == newItem.toString()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}

fun itemDivider(context: Context): MaterialDividerItemDecoration {
    return MaterialDividerItemDecoration(context, LinearLayoutManager.VERTICAL)
}