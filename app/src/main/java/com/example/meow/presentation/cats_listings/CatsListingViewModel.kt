package com.example.meow.presentation.cats_listings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meow.domain.repository.CatsRepository
import com.example.meow.util.CatsDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bharath on 10/28/2022.
 */

@HiltViewModel
class CatsListingViewModel @Inject constructor(private val repository: CatsRepository) :
    ViewModel() {
    var uiState: StateFlow<CatsDataState> = MutableStateFlow(CatsDataState.Loading)

    init {
        getCatsListing()
    }

    private fun getCatsListing(
        query: String = "",
    ) {
        viewModelScope.launch {
            uiState = repository.getCatsListing(query).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CatsDataState.Loading
            )
        }
    }
}