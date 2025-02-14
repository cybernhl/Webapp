package com.webapp.common.ui.splash

import com.webapp.common.def.MAIN
import com.webapp.common.def.Router
import com.webapp.common.def.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

public class SplashViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    public val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    public fun startCountdown() {
        viewModelScope.launch {
            delay(2000)
            _uiState.update { uiState ->
                uiState.copy(route = MAIN, splashState = false)
            }
        }
    }
}