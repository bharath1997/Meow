package com.example.meow.presentation.cat_details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.meow.domain.model.BreedInfo
import com.example.meow.presentation.cats_listings.CatDataWithLabelTemplate
import com.example.meow.presentation.cats_listings.CatPictureComposable
import com.example.meow.presentation.cats_listings.CatText
import com.example.meow.util.BreedDetailsState
import com.example.meow.util.CircularProgressBar
import com.example.meow.util.Loader
import com.example.meow.R
import com.example.meow.domain.model.RatingsUI
import com.example.meow.presentation.cats_listings.UnknownErrorComposable
import kotlin.math.min

/**
 * Created by Bharath on 10/31/2022.
 */

@Composable
fun CatDetails(
    breedDetailsState: BreedDetailsState,
    modifier: Modifier = Modifier,
    onUpClicked: () -> Unit,
) {
    when (breedDetailsState) {
        BreedDetailsState.Loading -> {
            Loader()
        }
        is BreedDetailsState.Success -> {
            CatDetailsScreen(breedDetailsState.breedInfo, onUpClicked = onUpClicked)
        }
        BreedDetailsState.Unknown
        -> {
            UnknownErrorComposable()
        }
    }
}

//stateless
@Composable
fun CatDetailsScreen(breedInfo: BreedInfo, modifier: Modifier = Modifier, onUpClicked: () -> Unit) {
    val scrollState = rememberScrollState()
    val offset = min(
        1f,
        1 - scrollState.value.div(600f)
    )

    val animateImageState by animateDpAsState(targetValue = max(80.dp, 150.dp * offset))
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .wrapContentHeight()
    ) {
        FilledIconButton(
            modifier = Modifier.padding(10.dp),
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.secondary),
            onClick = { onUpClicked() },
            shape = CircleShape
        ) {
            Icon(
                modifier = Modifier.clip(CircleShape),
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Row {
            CatPictureComposable(
                modifier = modifier,
                imageUrl = breedInfo.imageUrl,
                size = animateImageState
            )
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                CatText(
                    modifier = modifier.padding(8.dp),
                    text = breedInfo.breedName,
                    textStyle = MaterialTheme.typography.headlineLarge
                )
                CatDataWithLabelTemplate(
                    modifier = modifier.padding(8.dp),
                    label = "${stringResource(R.string.origin)}: ",
                    data = breedInfo.origin
                )
                CatDataWithLabelTemplate(
                    modifier = modifier.padding(8.dp),
                    label = "${stringResource(R.string.lifespan)}: ",
                    data = breedInfo.lifeSpan
                )
            }

        }

        CatDataWithLabelTemplate(
            modifier = modifier.padding(8.dp),
            label = "${stringResource(R.string.nature)}: ",
            data = breedInfo.nature
        )

        CatText(
            modifier = modifier.padding(16.dp),
            text = breedInfo.description,
            textStyle = MaterialTheme.typography.bodyMedium
        )
        Ratings(breedInfo.ratings)

    }
}


@Composable
fun Ratings(ratings: List<RatingsUI>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "${stringResource(id = R.string.ratings)}:",
            style = MaterialTheme.typography.labelLarge
        )
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 90.dp),
        modifier = Modifier
            .height(500.dp)
            .padding(10.dp),
        userScrollEnabled = false
    ) {
        items(ratings) { rating ->
            if (rating.percentage > 0) {
                CircularProgressBar(
                    rating.name,
                    percentage = rating.percentage,
                    number = 100,
                    animDelay = 100
                )
            }

        }

    }
}