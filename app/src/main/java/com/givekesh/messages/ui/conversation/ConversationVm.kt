package com.givekesh.messages.ui.conversation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ConversationVm @ViewModelInject constructor(
    @Assisted private val state: SavedStateHandle
) : ViewModel()
