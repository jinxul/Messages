package com.givekesh.messages.data.models

data class Messages(
    val _id: Int,
    val threadId: Long,
    val address: String,
    val body: String,
    val date: Long,
    val dateSent: Long,
    val messageBox: Int,
    val read: Boolean,
    val readStatus: Int,
)