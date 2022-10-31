package com.example.meow.presentation.cats_listings

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.example.meow.data.local.Ratings
import com.example.meow.domain.model.BreedInfo
import com.example.meow.ui.theme.MeowTheme
import com.example.meow.util.CatsDataState
import com.example.meow.util.DevicePreviews

/**
 * Created by Bharath on 10/30/2022.
 */
@Composable
fun CatsListStateless(catsListingsState: CatsDataState, modifier: Modifier = Modifier) {
    Log.d("CatsListStateless", "State $catsListingsState")
    when (catsListingsState) {
        is CatsDataState.Success -> {
            LazyColumn(modifier = modifier) {
                items(catsListingsState.catsList) {
                    CatItemComposable(it) {
                        //TODO handle on click
                    }
                }
            }
        }
        CatsDataState.Unknown -> {}
        CatsDataState.Loading -> {
            Loader()
        }
    }

}

@Composable
fun Loader() {
    val retrySignal = rememberLottieRetrySignal()

    val composition by rememberLottieComposition(LottieCompositionSpec.Url("https://assets6.lottiefiles.com/private_files/lf30_yuvwrfd2.json"),
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


@DevicePreviews
@Composable
fun CatsListStatelessPreview() {
    MeowTheme {
        CatsListStateless(
            catsListingsState = CatsDataState.Success(
                listOf(
                    BreedInfo(
                        breedName = "Test",
                        imageUrl = "https://cdn2.thecatapi.com/images/KWdLHmOqc.jpg",
                        origin = "India",
                        nature = "Test",
                        description = "Test shdjhsjdhf shdfkjshdkfjsh hskjdhfkjsh",
                        lifeSpan = "10",
                        alternativeNames = "Test test, sets",
                        ratings = Ratings(
                            indoor = 0,
                            lap = 0,
                            adaptability = 0,
                            affectionLevel = 0,
                            childFriendly = 0,
                            dogFriendly = 0,
                            energyLevel = 0,
                            grooming = 0,
                            healthIssues = 0,
                            intelligence = 0,
                            sheddingLevel = 0,
                            socialNeeds = 0,
                            strangerFriendly = 0,
                            vocalization = 0,
                            experimental = 0,
                            natural = 0,
                            rare = 0
                        )
                    )
                )
            )
        )
    }
}
