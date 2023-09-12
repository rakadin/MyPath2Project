package com.example.mypath2project.data

import androidx.annotation.DrawableRes

data class Email(
    val id: Long,
    val sender: String,
    val subject: String,
    val body: String,
    var isImportant: Boolean = false,
    var isStarred: Boolean = false,
    var mailbox: MailboxType = MailboxType.INBOX,
    val createdAt: String,
)

enum class MailboxType {
    INBOX, DRAFTS, SENT, SPAM, TRASH
}

data class EmailAttachment(
    @DrawableRes val resId: Int,
    val contentDesc: String
)