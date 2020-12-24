package com.givekesh.messages.ui.threads

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.givekesh.messages.BR
import com.givekesh.messages.data.models.Messages
import com.givekesh.messages.databinding.RowThreadBinding

class ThreadsAdapter :
    PagingDataAdapter<Messages, ThreadsViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ThreadsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowThreadBinding.inflate(inflater, parent, false)
        return ThreadsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ThreadsViewHolder, position: Int
    ) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Messages>() {
        override fun areItemsTheSame(
            oldItem: Messages, newItem: Messages
        ): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(
            oldItem: Messages, newItem: Messages
        ): Boolean {
            return oldItem == newItem
        }
    }
}

class ThreadsViewHolder(val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(messages: Messages) {
        binding.setVariable(BR.message, messages)
    }
}