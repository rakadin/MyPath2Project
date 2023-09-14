package com.example.mypath2project.viewmodels

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.mypath2project.data.Email
import com.example.mypath2project.data.MailboxType

class EmailViewModel : ViewModel() {
    private fun getEmailList() = sampleEmailData.emailSample
    private val _email = getEmailList().toMutableStateList()
    val emails: List<Email>
        get() =_email
    fun changeIsStarChanged(email: Email){
        emails.find { it.id == email.id}?.let { email ->
            email.isStarred = !email.isStarred
            Log.v("check_click","clicked star ${email.isStarred}")
        }
    }
    fun removeEmail(email: Email){
        _email.remove(email)
    }
}
object sampleEmailData{
    val emailSample= listOf<Email>(
        Email(
            101,
            "Quân",
        "Package shipped",
        "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            false,
    true,
            MailboxType.INBOX,
            "20 mins ago"
        ),
        Email(
                102,
        "Nhat Hoang Tran Long",
        "Package delivery",
        "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
        true,
        false,
        MailboxType.INBOX,
        "30 mins ago"
        ),
        Email(
            103,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            104,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            105,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            106,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            107,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            108,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            109,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
        Email(
            1010,
            "Nhat Hoang Tran Long",
            "Package delivery",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            true,
            false,
            MailboxType.INBOX,
            "30 mins ago"
        ),
    )
}