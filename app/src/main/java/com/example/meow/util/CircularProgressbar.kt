package com.example.meow.util

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Bharath on 10/31/2022.
 */
@Composable
fun CircularProgressBar(
    name: String?,
    percentage: Float,
    number: Int,
    radius: Dp = 32.dp,
    color: Color = MaterialTheme.colorScheme.tertiary,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {

    Log.d("percentage", percentage.toString())
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    //start animation
    val currentPercentage =
        animateFloatAsState(
            targetValue = if (animationPlayed) percentage else 0f,
            animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
        )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Column(Modifier.padding(5.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(radius * 2f)
                .padding(5.dp)
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * currentPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }

            Text(
                text = (currentPercentage.value * number).toInt().toString(),
                style = MaterialTheme.typography.labelSmall
            )
        }
        name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}