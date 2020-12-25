package com.givekesh.messages.ui.threads

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.givekesh.messages.data.source.MainRepository

class ThreadsVm @ViewModelInject constructor(
    mainRepository: MainRepository,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val messages = mainRepository.getConversationThreads().cachedIn(viewModelScope)
}
