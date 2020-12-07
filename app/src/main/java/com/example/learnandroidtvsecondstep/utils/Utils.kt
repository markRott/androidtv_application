package com.example.learnandroidtvsecondstep.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun <T> objectsNotNull(vararg objects: T, successAction: () -> Unit) {
    objects.all { it != null }.also {
        if (it) {
            successAction.invoke()
        }
    }
}

inline fun logDebug(tag: String, message: () -> String) { Log.d(tag, message()) }