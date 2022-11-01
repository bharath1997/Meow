package com.example.meow.presentation.cats_listings

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.meow.R
import com.example.meow.ui.theme.MeowTheme
import com.example.meow.util.CatsDataState
import com.example.meow.util.DevicePreviews
import com.example.meow.util.Loader
import com.example.meow.util.sampleBreedInfo

/**
 * Created by Bharath on 10/30/2022.
 */
@Composable
fun CatsListStateless(
    catsListingsState: CatsDataState,
    modifier: Modifier = Modifier,
    tryAgain: () -> Unit = {},
    onClickItem: (String) -> Unit
) {
    Log.d("CatsListStateless", "State $catsListingsState")
    when (catsListingsState) {
        is CatsDataState.Success -> {
            LazyColumn(modifier = modifier) {
                items(catsListingsState.catsList) {
                    CatItemComposable(breedInfo = it, onClick = onClickItem)
                }
            }
        }
        CatsDataState.Unknown -> {
            UnknownErrorComposable(tryAgain)
        }
        CatsDataState.Loading -> {Loader()
        }
    }

}


@Composable
fun UnknownErrorComposable(tryAgain: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_cat_error),
                contentDescription = "error with cat image",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.error)
            )
            Text(text = stringResource(id = R.string.error))
            Button(onClick = tryAgain) {
                Text(text = stringResource(id = R.string.try_again))
            }
        }
    }
}

@DevicePreviews
@Composable
fun CatsListStatelessPreview() {
    MeowTheme {
        CatsListStateless(
            catsListingsState = CatsDataState.Success(
                listOf(
                    sampleBreedInfo
                )
            )
        ) {}
    }
}
