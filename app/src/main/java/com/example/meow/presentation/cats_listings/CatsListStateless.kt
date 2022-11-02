package com.example.meow.presentation.cats_listings

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.meow.domain.model.BreedInfo
import com.example.meow.ui.theme.MeowTheme
import com.example.meow.util.CatsDataState
import com.example.meow.util.DevicePreviews
import com.example.meow.util.Loader
import com.example.meow.util.sampleBreedInfo

/**
 * Created by Bharath on 10/30/2022.
 */
@Composable
fun CatsList(
    catsListingsState: CatsDataState,
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit
) {
    Log.d("CatsListStateless", "State $catsListingsState")
    when (catsListingsState) {
        is CatsDataState.Success -> {
            CatsListingScreen(modifier, catsListingsState.catsList, onClickItem)
        }
        CatsDataState.Unknown -> {
            UnknownErrorComposable()
        }
        CatsDataState.Loading -> {
            Loader()
        }
    }

}


//stateless
@Composable
fun CatsListingScreen(
    modifier: Modifier = Modifier,
    breeds: List<BreedInfo>,
    onClickItem: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(breeds) {
            CatItemComposable(breedInfo = it, onClick = onClickItem)
        }
    }
}


@Composable
fun UnknownErrorComposable() {
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
        }
    }
}

@DevicePreviews
@Composable
fun CatsListStatelessPreview() {
    MeowTheme {
        CatsListingScreen(
            breeds =
            listOf(
                sampleBreedInfo,
                sampleBreedInfo,
                sampleBreedInfo
            )

        ) {}
    }
}
