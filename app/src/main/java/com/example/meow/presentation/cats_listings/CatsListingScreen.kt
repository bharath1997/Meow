package com.example.meow.presentation.cats_listings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.meow.presentation.destinations.CatDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Created by Bharath on 10/28/2022.
 */

//stateful
@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
@Destination(start = true)
fun CatsListingScreen(
    navigator: DestinationsNavigator,
    viewModel: CatsListingViewModel = hiltViewModel()
) {

    //snack can be used in future
    val snackbarHostState = remember { SnackbarHostState() }
    val catsListingsState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Text(
                text = "Meow",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(28.dp),
            )
        }) { paddingValues ->
        CatsList(
            modifier = Modifier.padding(paddingValues),
            catsListingsState = catsListingsState
        ) { id ->
            navigator.navigate(CatDetailsScreenDestination(id = id))
        }
    }
}


