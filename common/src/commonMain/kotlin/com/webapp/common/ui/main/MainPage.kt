package com.webapp.common.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.rememberWebViewState
import com.webapp.common.utils.AppConfig
import com.webapp.common.utils.LogUtils
import com.webapp.common.widget.MWebView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import moe.tlaster.precompose.koin.koinViewModel
//import moe.tlaster.precompose.viewmodel.viewModel
@Composable
//public fun MainPage(viewModel: MainViewModel) {
//Ref : https://iamgideon.medium.com/stop-passing-context-into-viewmodels-bb11b3f432fb
public fun MainPage() {
//    val viewModel = viewModel(MainViewModel::class) {
//        MainViewModel()
//    }
    val viewModel = koinViewModel(MainViewModel::class)
    val uiState = viewModel.uiState.collectAsState()
    LogUtils.e(message = "Main uiState : ${uiState.value}")
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            viewModel.fetchUrl(AppConfig.HOST)
            println("Show me value form VM : ${uiState.value.url}")
            println("Show me value form AppConfig : ${AppConfig.HOST}")
            val url = uiState.value.url
            val webViewState =
                rememberWebViewState(url)
            Column(Modifier.fillMaxSize()) {
                MWebView(
                    url,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}