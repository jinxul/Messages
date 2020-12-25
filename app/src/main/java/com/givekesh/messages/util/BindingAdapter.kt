package com.givekesh.messages.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.givekesh.messages.ui.threads.ThreadsFragmentDirections

object BindingAdapter {

    @BindingAdapter("onThreadItemClickListener")
    @JvmStatic
    fun onThreadItemClickListener(view: View, threadId: Long) {
        view.setOnClickListener {
            val action = ThreadsFragmentDirections
                .actionThreadsFragmentToConversationFragment(threadId)
            view.findNavController().navigate(action)
        }
    }
}