package com.webapp.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

import android.webkit.JavascriptInterface
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustEvent
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.serialization.*
import kotlinx.serialization.json.*

import java.util.*

public class ADjustJS constructor(private val context: Context) {

    @JavascriptInterface
    public fun getAppInfo(): String {
        val map = HashMap<String, String>()
//        map["app_token"] = BuildConfig.ADJUST_TOKEN
//        map["adid"] = LocalDatabase.adid
//        map["gps_adid"] = LocalDatabase.gpsadid

//        val gson = Gson()//
//        return gson.toJson(map) ?: ""
        //TODO 改用 kotlinx serialization
        return Json.encodeToString(map)?: ""
    }

    private fun getGoogleADID() : String? {
        val idInfo: AdvertisingIdClient.Info
        var id: String? = null

        try {
            idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
            id = idInfo.id
        } catch (e:Exception) {
            id = ""
        }
        return id
    }
    @JavascriptInterface
    public
            /**
     * 單事件的追蹤
     */
    fun onEvent(eventFun:String) {
        var evenToken = ""
        ///將輸入skywin事件改為appsflyer事件
        when(eventFun) {
            in "login" ->  evenToken = "ggozof"
            in "register" ->  evenToken = "lv9idm"

        }
        ///記錄
        val event = AdjustEvent(evenToken)
        Adjust.trackEvent(event);
    }


    /**
     * 單事件加上參數的追蹤
     * params: eventFun, amount, currency
     * 10	"Event timeout. Check 'minTimeBetweenSessions' param"
     * 11	"Skipping event because 'isStopTracking' enabled"
     * 40	Network error: Error description comes from Android
     * 41	"No dev key"
     * 50	"Status code failure" + actual response code from the server
     */
    @JavascriptInterface
    public fun onEvent(eventFun: String, amount: Double, currency: String) {
        var evenToken = ""
        ///將輸入skywin事件改為adjust事件
        when(eventFun) {
            in "apply-deposit" ->   evenToken = "j4131h"
            in "apply-withdrawal" ->  evenToken = "rjk24q"
            in "complete-withdrawal" ->  evenToken = "ytfvq9"
            in "complete-deposit" ->  evenToken = "jkuyni"
            in "first-complete-deposit" ->  evenToken = "930vot"
        }
        val event = AdjustEvent(evenToken)
        event.setRevenue(amount, currency);
        Adjust.trackEvent(event);

    }

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