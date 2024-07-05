package com.webapp.common

import androidx.compose.runtime.Composable
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.webapp.common.utils.AppConfig

public actual fun getPlatformName(): String {
    return "kmp"
}
public actual fun getFrpcVersion(): String {
//    return Frpclib.getVersion()
    return "1.0.0"
}
public actual  fun initAdjust() {
     println("android init Adjust")

    val adjustToken = AppConfig.ADJUSTTOEKN
    val environment = AdjustConfig.ENVIRONMENT_SANDBOX
    val config = AdjustConfig(AndroidBaseEnv.context(),adjustToken, environment)
    config.setSendInBackground(true)
    config.setEventBufferingEnabled(true)
    config.setLogLevel(LogLevel.VERBOSE)
    Adjust.onCreate(config)
}