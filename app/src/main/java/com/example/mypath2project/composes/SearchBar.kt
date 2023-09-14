package com.example.mypath2project.composes

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mypath2project.R
import com.example.mypath2project.ui.theme.shapes

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchingBar() {
    val infinitiveTransition =  rememberInfiniteTransition()
    val rotationAnimation = infinitiveTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing))
    )
    val rainbowColors = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Magenta
    )

    val rainbowColorBrush = Brush.linearGradient(
        colors = rainbowColors,
        start = Offset(0f, 0f),
        end = Offset(100f, 100f)
    )

    var text by rememberSaveable {
        mutableStateOf("")
    }
    Surface(

    ) {
        Row(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            TextField(
                value = text,
                onValueChange = {text = it
                                Log.v("check_value","$text")},
                placeholder = { Text(text = "Search...")},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null,
                        modifier = Modifier.padding(horizontal = 16.dp))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(
                            brush = Brush.linearGradient(
                                colors = listOf(Color.Cyan, Color.Yellow),
                                start = Offset(0f, 0f),
                                end = Offset(100f, 100f)
                            ),
                            width = 2.dp
                        ),
                        shape = CutCornerShape(12.dp)
                    ),
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.flower),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clip(CircleShape)
                            .size(30.dp)
                            .drawBehind {
                                rotate(rotationAnimation.value){
                                    drawCircle(rainbowColorBrush, style = Stroke(0.5f))
                                }
                            }
                    )
                }
            )

        }
    }

}