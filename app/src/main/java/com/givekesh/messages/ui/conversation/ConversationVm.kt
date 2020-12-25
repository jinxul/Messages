package com.givekesh.messages.ui.conversation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.givekesh.messages.data.models.Messages
import com.givekesh.messages.data.source.MainRepository

class ConversationVm @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    lateinit var messages: LiveData<PagingData<Messages>>

    fun getMessages(threadId: Long) {
        messages = mainRepository.getConversationsFromThreadId(threadId).cachedIn(viewModelScope)
    }
}
