package com.givekesh.messages.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.givekesh.messages.data.source.local.ContentApi
import com.givekesh.messages.util.Constant

class MainRepository constructor(
    private val contentApi: ContentApi
) {
    fun getConversationThreads() = Pager(
        config = PagingConfig(
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ThreadPagingSource(contentApi) }
    ).liveData

    fun getConversationsFromThreadId(threadId: Long) = Pager(
        config = PagingConfig(
            pageSize = Constant.PAGE_SIZE,
            maxSize = Constant.MAX_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ConversationPagingSource(contentApi, threadId) }
    ).liveData
}