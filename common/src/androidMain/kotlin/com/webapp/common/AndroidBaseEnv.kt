package com.webapp.common

import android.content.Context

public object AndroidBaseEnv {

   public var mContext: Context? = null
        set(value) {
            field = value!!.applicationContext
        }

   public fun context(): Context = mContext!!

}