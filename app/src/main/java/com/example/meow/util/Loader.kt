package com.example.meow.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.*

/**
 * Created by Bharath on 10/31/2022.
 */
@Composable
fun Loader() {
    val retrySignal = rememberLottieRetrySignal()

    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url("https://assets6.lottiefiles.com/private_files/lf30_yuvwrfd2.json"),
        onRetry = { failCount, exception ->
            retrySignal.awaitRetry()
            // Continue retrying. Return false to stop trying.
            true
        })

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
    )
    LottieAnimation(composition = composition, progress = { progress })
}