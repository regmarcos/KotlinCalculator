package com.example.kotlincalculator.mvp.view

import android.app.Activity
import android.content.Context
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) : Activity() {
    private val activityRef: WeakReference<Activity> = WeakReference(activity)
    val activity: Activity
        get() = activityRef.get()!!
    val context: Context
        get() = activity

}