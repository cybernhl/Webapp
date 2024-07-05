package com.webapp.common.jsbridge

import com.multiplatform.webview.jsbridge.WebViewJsBridge

public class CustomWebviewJsBridge: WebViewJsBridge()  {
    init{
        register(JsMessageHandler())
    }
}