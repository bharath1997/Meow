package com.example.meow.presentation.cat_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meow.domain.repository.CatsRepository
import com.example.meow.util.BreedDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bharath on 10/31/2022.
 */

@HiltViewModel
class CatDetailsViewModel @Inject constructor(
    private val repository: CatsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState: StateFlow<BreedDetailsState> = MutableStateFlow(BreedDetailsState.Loading)

    init {
        val id = savedStateHandle.get<String>("id") ?: ""
        getBreedDetails(id)
    }

    private fun getBreedDetails(
        id: String
    ) {
        viewModelScope.launch {
            uiState = repository.getBreed(id).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BreedDetailsState.Loading
            )
        }
    }

}