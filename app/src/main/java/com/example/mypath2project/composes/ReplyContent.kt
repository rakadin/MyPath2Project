package com.example.mypath2project.composes

import androidx.compose.ui.res.vectorResource
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypath2project.R
import com.example.mypath2project.data.Email
import com.example.mypath2project.data.MailboxType
import com.example.mypath2project.viewmodels.EmailViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.Log
import com.example.mypath2project.ui.theme.MyDarkTheme1
import com.example.mypath2project.ui.theme.MyLightTheme1
import kotlinx.coroutines.delay

class ReplyContent : ComponentActivity() {

    @Preview
    @Composable
    fun previewScreen() {
        EmailScreen()
    }

    @Composable
    fun EmailScreen(modifier: Modifier = Modifier,
                    emailViewModel: EmailViewModel = viewModel()) {
        var skeletonLoading by remember {
            mutableStateOf(false)
        }
        suspend fun loadingSkeleton() {
            if (!skeletonLoading) {
                skeletonLoading = true
                delay(3000L)
                skeletonLoading = false
            }
        }
        LaunchedEffect(Unit){
            loadingSkeleton()
        }
        if(skeletonLoading){
            LoadingRow()
        }
        else{
            EmailList(list = emailViewModel.emails, changeStared = {email -> emailViewModel.changeIsStarChanged(email)})
        }
    }

    @Composable
    fun EmailList(
        list: List<Email>,
        changeStared: (Email) -> Unit,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(modifier = modifier){
            items( items = list,
                key = {temp_email-> temp_email.id}){
                    email -> EmailIemInfomation(email = email,
                        changeStared = {changeStared(email)})
            }
        }
    }

    @Preview
    @Composable
    fun PreviewEmailListInDarkMode() {
        MyDarkTheme1 {
            EmailScreen()
        }
    }
    @Preview
    @Composable
    fun PreviewEmailListInLightMode() {
        MyLightTheme1 {
            EmailScreen()
        }
    }
    @Preview
    @Composable
    fun PreviewEmailInfo() {
        val email: Email = Email(
            101,
            "Quân",
            "Package shipped",
            "Media player have been shipped to your house so let get home and get that right now, Media player have been shipped to your house, Media player have been shipped to your house",
            false,
            true,
            MailboxType.INBOX,
            "20 mins ago"
        )
        EmailIemInfomation(email = email, changeStared = {email.isStarred} )
    }

    @Composable
    fun EmailIemInfomation(
        email: Email,
        modifier: Modifier = Modifier.fillMaxSize(),
        changeStared: () -> Unit
    ) {
        Surface(color = MaterialTheme.colorScheme.primary) {
            Column(
                modifier = modifier.padding(top = 6.dp)
            ) {
                ContentItemHeader(email = email, changeStared = { changeStared() })
            }
        }

    }

    @Preview
    @Composable
    fun PreviewContentInfo() {
        val email: Email = Email(
            101,
            "Quân",
            "Package shipped",
            "Media player have been shipped to your house, Media player have been shipped to your house, Media player have been shipped to your house",
            false,
            true,
            MailboxType.INBOX,
            "20 mins ago"
        )
        ContentInfo(email = email)
    }

    @Composable
    fun ContentInfo(
        email: Email,
        modifier: Modifier = Modifier.fillMaxWidth()
    ) {
        var isExpaned by rememberSaveable { mutableStateOf(false) }
        val extraPadding by animateDpAsState(
            if (isExpaned) 20.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        Column(
            modifier = modifier
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                .clickable(onClick = { isExpaned = !isExpaned }
                )

        ) {
            Row(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = email.subject,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant                )
            }
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = email.body,
                    maxLines = if (isExpaned) Int.MAX_VALUE else 2,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }

    @Composable
    fun ContentItemHeader(
        email: Email,
        changeStared: () -> Unit,
        modifier: Modifier = Modifier,

    ) {
        var isStarred by rememberSaveable { mutableStateOf(email.isStarred) }

        Card(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ReplyEmailImage(imaRes = R.drawable.vegetable, description = "Quân")
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = email.sender
                        )
                        Text(
                            text = email.createdAt
                        )
                    }
                    Image(
                        painter = if (isStarred == true) {
                           painterResource(id = R.drawable.is_started)
                        } else {
                            painterResource(id = R.drawable.is_unstar)
                        },
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(onClick = {
                                changeStared()// save it in viewmodel
                                isStarred = !isStarred
                            })
                    )

                }
                ContentInfo(email = email)
            }
        }
    }


    @Composable
    fun ReplyEmailImage(
        @DrawableRes imaRes: Int,
        description: String,
        modifier: Modifier = Modifier
    ) {
        Image(
            painter = painterResource(id = imaRes),
            contentScale = ContentScale.Crop,
            contentDescription = description,
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
    /**
     * Shows the loading state
     */
    @Composable
    private fun LoadingRow() {
        // Creates an `InfiniteTransition` that runs infinite child animation values.
        val infiniteTransition = rememberInfiniteTransition()
        val alpha by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            // `infiniteRepeatable` repeats the specified duration-based `AnimationSpec` infinitely.
            animationSpec = infiniteRepeatable(
                // The `keyframes` animates the value by specifying multiple timestamps.
                animation = keyframes {
                    // One iteration is 1000 milliseconds.
                    durationMillis = 1000
                    // 0.7f at the middle of an iteration.
                    0.7f at 500
                },
                // When the value finishes animating from 0f to 1f, it repeats by reversing the
                // animation direction.
                repeatMode = RepeatMode.Reverse
            )
        )
        Row(
            modifier = Modifier
                .heightIn(min = 64.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = alpha))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(Color.LightGray.copy(alpha = alpha))
            )
        }
    }
}

