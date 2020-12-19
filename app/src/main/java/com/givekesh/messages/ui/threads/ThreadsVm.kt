package com.givekesh.messages.ui.threads

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ThreadsVm @ViewModelInject constructor(
    @Assisted private val state: SavedStateHandle
) : ViewModel()
