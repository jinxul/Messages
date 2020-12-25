package com.givekesh.messages.data.source

import androidx.paging.PagingSource
import com.givekesh.messages.data.models.Messages
import com.givekesh.messages.data.source.local.ContentApi
import com.givekesh.messages.util.Constant

class ConversationPagingSource(
    private val contentApi: ContentApi,
    private val threadId: Long
) : PagingSource<Int, Messages>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Messages> {
        val position = params.key ?: 1
        val response = contentApi.getConversationsFromThreadId(
            threadId = threadId,
            offset = (position - 1) * Constant.LIMIT
        )
        return LoadResult.Page(
            data = response,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1
        )
    }
}