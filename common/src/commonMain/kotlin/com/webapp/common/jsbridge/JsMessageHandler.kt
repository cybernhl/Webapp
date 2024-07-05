package com.webapp.common.jsbridge

import com.multiplatform.webview.jsbridge.IJsMessageHandler
import com.multiplatform.webview.jsbridge.JsMessage
import com.multiplatform.webview.jsbridge.dataToJsonString
import com.multiplatform.webview.jsbridge.processParams
import com.multiplatform.webview.web.WebViewNavigator
import com.webapp.common.model.SkywinModel

public class JsMessageHandler : IJsMessageHandler {
    public override  fun methodName(): String {
        return "skywinApp"
    }

    public override  fun handle(
        message: JsMessage,
        navigator: WebViewNavigator?,
        callback: (String) -> Unit
    ){
        println("skywinApp Handler get messege : $message")
        val param = processParams<SkywinModel>(message)
        val data = SkywinModel("KMM Received ${param.message}")
        callback(dataToJsonString(data))
    }
}