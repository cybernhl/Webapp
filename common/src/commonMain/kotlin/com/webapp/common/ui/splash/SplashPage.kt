package com.webapp.common.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.webapp.common.def.MAIN
import com.webapp.common.def.Router
import com.webapp.common.def.UiState
import com.webapp.common.generated.resources.Res
import com.webapp.common.generated.resources.start_up
import com.webapp.common.getFrpcVersion
import com.webapp.common.initAdjust
import com.webapp.common.ui.main.MainViewModel
import com.webapp.common.utils.LogUtils
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.resources.painterResource

@Composable
public fun SplashPage() {
    val x = getFrpcVersion()
    initAdjust()
    val startupLogo = remember { mutableStateOf(true) }
    val viewModel = koinViewModel( SplashViewModel::class)
    val uiState = viewModel.uiState.collectAsState()
    uiState.value.route?.let {
        Router.navigateTo(it)
    }

    Surface {
          Box(modifier = Modifier.fillMaxSize()){
              Image(
                  modifier = Modifier.fillMaxSize(),
                  painter = painterResource(Res.drawable.start_up),
                  contentScale = ContentScale.Crop,
                  contentDescription = "startup logo"
              )
          }
    }
    viewModel.startCountdown()
}