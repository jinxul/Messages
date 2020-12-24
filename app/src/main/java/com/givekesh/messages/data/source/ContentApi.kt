package com.givekesh.messages.data.source

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
    fun getThreads(offset: Int): List<Messages> {
        val messageList: MutableList<Messages> = mutableListOf()
        val cursor = getThreadCursor(offset)
        while (cursor.moveToNext()) {
            val message = mapper.mapFromEntity(cursor)
            messageList.add(message)
        }
        return messageList
    }

    private fun getThreadCursor(offset: Int): Cursor = contentResolver
        .query(
            Uri.parse("content://mms-sms/conversations/"),
            arrayOf("*"),
            null,
            null,
            "date DESC, date_sent DESC LIMIT ${Constant.LIMIT} OFFSET $offset"
        )!!
}