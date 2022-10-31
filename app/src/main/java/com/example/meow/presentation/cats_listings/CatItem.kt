package com.example.meow.presentation.cats_listings

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.meow.data.local.Ratings
import com.example.meow.domain.model.BreedInfo
import com.example.meow.ui.theme.MeowTheme
import com.example.meow.util.DevicePreviews

/**
 * Created by Bharath on 10/29/2022.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatItemComposable(breedInfo: BreedInfo, modifier: Modifier = Modifier, onClick: () -> Unit) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.Transparent)
    ) {
        val (catPicture, card) = createRefs()
        val topGuideline = createGuidelineFromTop(0.58f)
        Card(
            modifier = modifier
                .constrainAs(card) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .fillMaxWidth()
                .wrapContentSize()
                .clip(RoundedCornerShape(4.dp))
                .padding(16.dp),
            onClick = onClick
        ) {
            ConstraintLayout(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
            ) {
                val (cardTitle, nature, origin, lifespan) = createRefs()
                CatText(modifier = modifier.constrainAs(cardTitle) {
                    start.linkTo(parent.start, 108.dp)
                    top.linkTo(parent.top, 16.dp)
                }, breedInfo.breedName)
                CatDataWithLabelTemplate(modifier = modifier
                    .constrainAs(nature) {
                        start.linkTo(parent.start, 45.dp)
                        end.linkTo(parent.end, 32.dp)
                        top.linkTo(origin.bottom, 16.dp)
                        bottom.linkTo(parent.bottom, 16.dp)
                    }
                    .fillMaxWidth(), label = "Nature: ", breedInfo.nature)
                CatDataWithLabelTemplate(modifier = modifier.constrainAs(origin) {
                    start.linkTo(cardTitle.start)
                    top.linkTo(cardTitle.bottom, 16.dp)
                }, label = "Origin: ", breedInfo.origin)
                CatDataWithLabelTemplate(modifier = modifier.constrainAs(lifespan) {
                    start.linkTo(origin.end, 16.dp)
                    top.linkTo(cardTitle.bottom, 16.dp)
                }, label = "LifeSpan in years: ", breedInfo.lifeSpan)
            }
        }

        CatPictureComposable(modifier = modifier
            .constrainAs(catPicture) {
                start.linkTo(card.start, 24.dp)
                bottom.linkTo(topGuideline)
            }
            .wrapContentSize(), breedInfo.imageUrl)
    }
}

@Composable
fun CatIcon(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader by lazy {
        ImageLoader.Builder(context)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }
    AsyncImage(
        placeholder = rememberAsyncImagePainter(
            model = com.example.meow.R.drawable.catrunning,
            imageLoader = imageLoader
        ),
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = "cat breed image",
        modifier = modifier
            .clip(CircleShape)
            .size(86.dp)
    )
}

@Composable
fun CatPictureComposable(modifier: Modifier = Modifier, imageUrl: String) {
    ElevatedCard(
        shape = CircleShape,
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        modifier = modifier
    ) {
        CatIcon(imageUrl)
    }
}

@Composable
fun CatText(modifier: Modifier = Modifier, breedName: String?) {
    Text(
        text = breedName ?: "",
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun CatDataWithLabelTemplate(modifier: Modifier = Modifier, label: String, data: String) {
    Row(modifier) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Text(data, style = MaterialTheme.typography.bodySmall, maxLines = 2)
    }

}


@DevicePreviews
@Composable
fun CatIcon() {
    BoxWithConstraints {
        MeowTheme {
            CatItemComposable(
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
            ) {}
        }

    }
}