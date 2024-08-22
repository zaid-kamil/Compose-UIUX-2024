package com.digi.composeuiux.ui.screens

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnimationThree(modifier: Modifier = Modifier) {
    val infiniteTextTransition = rememberInfiniteTransition(
        label = "Infinite Text Transition"
    )

    val animateColor by infiniteTextTransition.animateColor(
        initialValue = Color(0xFFFF8A65),
        targetValue = Color(0xFF046CEC),
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Color animation"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Animation 3",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.ExtraBold,
            color = animateColor
        )
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp)
                .background(
                    animateColor,
                    MaterialTheme.shapes.extraLarge
                ),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Box", style = MaterialTheme.typography.displayMedium)
        }
    }
}