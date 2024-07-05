package com.webapp.common.ui.main

import com.webapp.common.def.SPLASH
import com.webapp.common.def.UiState
import com.webapp.common.ui.splash.SplashPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

public class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    public val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    public fun fetchUrl(urlPath:String) {
        viewModelScope.launch {
            _uiState.update {
                //FIXME def form buildconfig ?
                it.copy(url = urlPath, route = SPLASH)
            }
        }
    }
}