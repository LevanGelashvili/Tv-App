package com.balevanciaga.tvapp.custom.ext

import android.util.Log
import androidx.lifecycle.ViewModel

private const val LOG_KEY = "MVI"

fun <T> T.log(key: String = LOG_KEY): T {
    Log.e(key, toString())
    return this
}

fun Throwable.logMessage() {
    ((message ?: "") + "\n" + stackTraceToString()).log()
}

fun ViewModel.logAction(action: Any) {
    "${tag()} - Action - ${action.javaClass.simpleName}".log()
}