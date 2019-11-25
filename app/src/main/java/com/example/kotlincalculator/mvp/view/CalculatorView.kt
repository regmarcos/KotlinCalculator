package com.example.kotlincalculator.mvp.view

import android.app.Activity
import kotlinx.android.synthetic.main.activity_main.visor_result
import kotlinx.android.synthetic.main.activity_main.visor_operation

class CalculatorView (activity: Activity) : ActivityView(activity) {
    fun setVisor(visor : String){
        activity?.visor_operation?.text = visor
    }
    fun showResult(result : String){
        activity?.visor_result?.text  = result
    }
}