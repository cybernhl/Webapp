package com.webapp.common.utils
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
//FIXME https://github.com/AAkira/Napier
public object LogUtils {
    private var TAG: String = "Init you TAG"
    public fun init(tag: String? = null) {
        if (tag != null) {
            TAG= tag
        }
        Napier.base(DebugAntilog())
    }

    public fun w(tag: String? = null, message: String) {
        tag?.let {
            Napier.w(message, null, it)
        } ?: run {
            Napier.w(message, null, TAG)
        }
    }

    public fun i(tag: String? = null, message: String) {
        tag?.let {
            Napier.i(message, null, tag)
        } ?: run {
            Napier.i(message, null,  TAG)
        }
    }

    public fun d(tag: String? = null, message: String) {
        tag?.let {
            Napier.d(message, null, tag)
        } ?: run {
            Napier.d(message, null,  TAG)
        }
    }

    public fun e(tag: String? = null, message: String, throwable: Throwable?=null) {
        tag?.let {
            Napier.e(message, throwable, tag)
        } ?: run {
            Napier.e(message, throwable,  TAG)
        }
    }
}