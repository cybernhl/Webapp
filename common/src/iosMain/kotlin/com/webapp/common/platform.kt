package com.webapp.common

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController
import com.adjust.*
import com.webapp.common.utils.AppConfig
import com.webapp.common.utils.LogUtils
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

public actual fun getPlatformName(): String {
    return "iOS"
}

@OptIn(ExperimentalForeignApi::class)
public actual fun getFrpcVersion(): String {
    return com.frpclib.FrpclibGetVersion()
}

@OptIn(ExperimentalForeignApi::class)
public actual fun initAdjust()  {
    val adjToken = AppConfig.ADJUSTTOEKN
    LogUtils.d(message = "kt actual ios adjust token is $adjToken")
    val evironment = ADJEnvironmentSandbox
    val config = ADJConfig(adjToken, evironment)
    config.logLevel = ADJLogLevelVerbose
    Adjust.appDidLaunch(config)
}
///init LogUtils，提供日志打印
public fun debugBuild() {
    LogUtils.init("myios")
}