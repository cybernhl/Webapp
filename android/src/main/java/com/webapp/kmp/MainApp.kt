package com.webapp.kmp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.webapp.common.AndroidBaseEnv
import com.webapp.kmp.BuildConfig
import com.webapp.common.di.viewModelModule
import com.webapp.common.utils.AppConfig
import com.webapp.common.utils.LogUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApp : Application() , Application.ActivityLifecycleCallbacks {
    private val handler = Handler(Looper.myLooper()!!)

    private lateinit var callAdjustId: Runnable

    private var startTime = 0L

    private var endTime = 0L
    init {
        startTime = System.currentTimeMillis()

        callAdjustId = Runnable {
            val id = Adjust.getAdid()
            println("Adjust ID: $id")
            if (id.isNullOrEmpty()) {
                handler.postDelayed(this.callAdjustId, 500L)
            } else {
                endTime = System.currentTimeMillis()
                val runTime = endTime - startTime
                println("取得ID時間: ${runTime}ms")
            }
        }
    }
    companion object {
        lateinit var instance: MainApp
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        AndroidBaseEnv.mContext = this
        registerActivityLifecycleCallbacks(this)
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        LogUtils.init("Android Tag")
        startKoin {
            // Log Koin into Android logger
            androidLogger(Level.DEBUG)
            // Reference Android context
            androidContext(this@MainApp)
            // Load modules
            modules(listOf(
                viewModelModule
            ))
        }
        LogUtils.d(message = "Show me init AppConfig host ${AppConfig.HOST} ")
        AppConfig.HOST= BuildConfig.HostName
        LogUtils.d(message = "Show me refresh AppConfig host ${AppConfig.HOST} ")
        ///初始化adjust
        val adjustToken = BuildConfig.ADJUST_TOKEN
        AppConfig.ADJUSTTOEKN = BuildConfig.ADJUST_TOKEN
    }


    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityResumed(p0: Activity) {
        Adjust.onResume()
    }

    override fun onActivityPaused(p0: Activity) {
        Adjust.onPause()
    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {
        
    }
}