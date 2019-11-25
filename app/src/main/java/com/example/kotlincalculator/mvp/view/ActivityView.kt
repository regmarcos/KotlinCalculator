package com.example.kotlincalculator.mvp.view

import android.app.Activity
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) : Activity() {
    private val activityRef: WeakReference<Activity> = WeakReference(activity)
     open val activity: Activity?
        get() = activityRef.get()
    val context: Activity?
        get() = activity
}