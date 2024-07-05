package com.webapp.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.webapp.common.def.MAIN
import com.webapp.common.def.Router
import com.webapp.common.def.SPLASH
import com.webapp.common.utils.AppConfig
import com.webapp.common.utils.LogUtils
import com.webapp.common.def.TEST
import io.github.aakira.napier.Napier
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.koin.compose.KoinContext

@Composable
internal fun App() {
     PreComposeApp {
        KoinContext {
            MaterialTheme {
                LogUtils.d(message = "Demo debug debug mode")
                LogUtils.i(tag="new Tag",message = "Demo debug debug mode")
                LogUtils.d(message = "Show me  AppConfig host @App.kt ${AppConfig.HOST} ")
                val navigator = rememberNavigator()
                Router.initNavigation(navigator)
                NavHost(
                    navigator = navigator,
                    initialRoute = SPLASH.route
                ) {
                    SPLASH.registerRoute(this)
                    MAIN.registerRoute(this)
                    TEST.registerRoute(this)
                }
//                Surface {
//                    Box(
//                        modifier = Modifier.fillMaxSize(),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Text(getPlatformName())
//                    }
//                }
            }
        }
    }
}