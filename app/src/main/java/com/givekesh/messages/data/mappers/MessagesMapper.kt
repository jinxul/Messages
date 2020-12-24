package com.givekesh.messages.data.mappers

import android.database.Cursor
import com.givekesh.messages.data.models.Messages
import com.givekesh.messages.util.EntityMapper
import javax.inject.Inject

class MessagesMapper @Inject constructor(
) : EntityMapper<Cursor, Messages> {

    override fun mapFromEntity(entity: Cursor): Messages =
        Messages(
            _id = entity.getInt(
                entity.getColumnIndex("_id")
            ),
            threadId = entity.getLong(
                entity.getColumnIndex("thread_id")
            ),
            address = entity.getString(
                entity.getColumnIndex("address")
            ),
            body = entity.getString(
                entity.getColumnIndex("body")
            ),
            date = entity.getLong(
                entity.getColumnIndex("date")
            ),
            dateSent = entity.getLong(
                entity.getColumnIndex("date_sent")
            ),
            messageBox = entity.getInt(
                entity.getColumnIndex("msg_box")
            ),
            read = entity.getInt(
                entity.getColumnIndex("read")
            ) == 1,
            readStatus = entity.getInt(
                entity.getColumnIndex("read_status")
            )
        )
}