package com.example.mypath2project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypath2project.composes.BottomNavigation
import com.example.mypath2project.composes.ReplyContent
import com.example.mypath2project.composes.SearchingBar
import com.example.mypath2project.ui.theme.MyDarkTheme1
import com.example.mypath2project.ui.theme.MyLightTheme1
import com.example.mypath2project.ui.theme.MyPath2ProjectTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDarkTheme1() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val replyContent : ReplyContent = ReplyContent()
                    Scaffold (
                        content = { innerPadding ->
                            Column {
                                SearchingBar() // Place the SearchingBar inside the Column
                                //Spacer(modifier = Modifier.height(8.dp)) // Add spacing if needed
                                replyContent.EmailScreen(Modifier.padding(innerPadding))
                            }
                        },
                        bottomBar = { BottomNavigation() },
                        )
                }
            }

            }
        }
    }