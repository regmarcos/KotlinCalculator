package com.example.kotlincalculator.mvp.model

import com.example.kotlincalculator.utils.EMPTY_STRING

class CalculatorModel {
    var firstOperand : String = EMPTY_STRING
    var secondOperand : String = EMPTY_STRING
    var operator : String = EMPTY_STRING
    var result : String = EMPTY_STRING

    fun cleanVisor(){
        firstOperand = EMPTY_STRING
        secondOperand = EMPTY_STRING
        operator = EMPTY_STRING
        result = EMPTY_STRING
    }




}