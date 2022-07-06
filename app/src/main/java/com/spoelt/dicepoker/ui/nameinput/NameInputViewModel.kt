package com.spoelt.dicepoker.ui.nameinput

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spoelt.dicepoker.navArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NameInputViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState: MutableStateFlow<NameInputViewState> =
        MutableStateFlow(NameInputViewState.Initial)
    val viewState: StateFlow<NameInputViewState> = _viewState

    init {
        _viewState.value = NameInputViewState.LoadedNavArgument(
            gameOptions = savedStateHandle.navArgs()
        )
    }
}
