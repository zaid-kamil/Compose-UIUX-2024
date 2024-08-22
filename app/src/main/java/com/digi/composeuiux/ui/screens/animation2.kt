package com.digi.composeuiux.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun AnimationTwo(modifier: Modifier = Modifier) {
    var isClicked by remember { mutableStateOf(false) }
    val firstColor = Color.Cyan.copy(alpha = .5f)
    val secondColor = Color.Yellow.copy(alpha = .5f)
    val animatedColor by animateColorAsState(
        targetValue = if (isClicked) firstColor else secondColor,
        label = "Card Color",
    )
    val animateSize by animateDpAsState(
        targetValue = if (isClicked) 400.dp else 200.dp,
        label = "Card Size",
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 500
        )
    )
    val animateOffset by animateIntOffsetAsState(
        targetValue = if (isClicked) IntOffset(0, -100) else IntOffset(0, 100),
        label = "Card Offset",
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .size(animateSize)
                .offset { animateOffset },
            colors = CardDefaults.cardColors(containerColor = animatedColor),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = modifier
                        .background(Color.White, shape = CircleShape)
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 700,
                                delayMillis = 500
                            )
                        )
                        .size(if (isClicked) 200.dp else 100.dp)
                )
            }
        }
        Button(onClick = {
            isClicked = !isClicked
        }) {
            Text(text = "Animate")
        }

    }
}