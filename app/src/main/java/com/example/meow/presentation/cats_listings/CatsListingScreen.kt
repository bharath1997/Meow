package com.example.meow.presentation.cats_listings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.meow.util.CatsDataState
import com.ramcosta.composedestinations.annotation.Destination

/**
 * Created by Bharath on 10/28/2022.
 */

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
@Destination(start = true)
fun CatsListingScreen(
    modifier: Modifier = Modifier,
    viewModel: CatsListingViewModel = hiltViewModel()
) {
    val catsListingsState by viewModel.uiState.collectAsStateWithLifecycle()
    Column {

        CatsListStateless(catsListingsState = catsListingsState,modifier.padding(top = 16.dp))
    }
}


