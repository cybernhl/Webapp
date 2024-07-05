package com.webapp.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface

//FIXME 這邊用到context 建議 改用 context helper之類寫法 ?
public class JsFunction constructor(private val context: Context) {
    /**
     * 簡單測試window open function
     *
     */
    @JavascriptInterface
    public fun openDefaultBrowser(url:String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}