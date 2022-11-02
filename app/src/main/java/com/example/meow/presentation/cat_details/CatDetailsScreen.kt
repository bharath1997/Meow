package com.example.meow.presentation.cat_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Created by Bharath on 10/31/2022.
 */

//stateful composable
@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination
@Composable
fun CatDetailsScreen(
    id: String,
    navigator: DestinationsNavigator,
    viewModel: CatDetailsViewModel = hiltViewModel()
) {
    val breedDetailsState by viewModel.uiState.collectAsStateWithLifecycle()
    CatDetails(
        breedDetailsState,
        modifier = Modifier.fillMaxSize()
    ) { navigator.popBackStack() }
}