package com.givekesh.messages.data.source.local

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import com.givekesh.messages.data.mappers.MessagesMapper
import com.givekesh.messages.data.models.Messages
import com.givekesh.messages.util.Constant

class ContentApi(
    private val contentResolver: ContentResolver,
    private val mapper: MessagesMapper
) {
    fun getConversationThreads(offset: Int): List<Messages> =
        getMessages(offset)

    fun getConversationsFromThreadId(threadId: Long, offset: Int): List<Messages> =
        getMessages(threadId, offset)

    private fun getMessages(offset: Int): List<Messages> =
        getMessages(
            uri = Uri.parse("content://mms-sms/conversations/"),
            offset = offset
        )

    private fun getMessages(threadId: Long, offset: Int): List<Messages> =
        getMessages(
            uri = Uri.parse("content://sms/"),
            offset = offset,
            selection = "thread_id =?",
            selectionArgs = arrayOf(threadId.toString())
        )

    private fun getMessages(
        uri: Uri,
        offset: Int,
        selection: String? = null,
        selectionArgs: Array<String>? = null
    ): List<Messages> {
        val messageList: MutableList<Messages> = mutableListOf()
        val cursor = getCursor(uri, offset, selection, selectionArgs)
        while (cursor.moveToNext()) {
            val message = mapper.mapFromEntity(cursor)
            messageList.add(message)
        }
        return messageList
    }

    private fun getCursor(
        uri: Uri,
        offset: Int,
        selection: String?,
        selectionArgs: Array<String>?
    ): Cursor = contentResolver
        .query(
            uri,
            arrayOf("*"),
            selection,
            selectionArgs,
            "date DESC, date_sent DESC LIMIT ${Constant.LIMIT} OFFSET $offset"
        )!!
}